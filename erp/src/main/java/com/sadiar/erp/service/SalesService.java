package com.sadiar.erp.service;

import com.sadiar.erp.dto.CustomerCheckoutRequest;
import com.sadiar.erp.entity.*;
import com.sadiar.erp.repository.ICustomerRepo;
import com.sadiar.erp.repository.IProductRepo;
import com.sadiar.erp.repository.ISalesRepo;
import com.sadiar.erp.repository.IStockTransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    private ISalesRepo salesRepository;

    @Autowired
    private IProductRepo productRepository;

    @Autowired
    private IStockTransactionRepo transactionRepository;

    @Autowired
    private ICustomerRepo customerRepository;

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales getSalesById(Long id) {
        return salesRepository.findById(id).orElse(null);
    }

    @Transactional
    public Sales createSales(Sales sales) {
        sales.setSalesDate(new Date());
        sales.setStatus(SalesStatus.PENDING);

        double totalAmount = 0;

        for (SalesItem item : sales.getItems()) {
            item.setSales(sales);

            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + item.getProduct().getId()));

            if (product.getStockQty() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            // Reduce stock
            product.setStockQty(product.getStockQty() - item.getQuantity());
            productRepository.save(product);

            // Create StockTransaction
            StockTransaction transaction = new StockTransaction();
            transaction.setProduct(product);
            transaction.setQuantity(item.getQuantity());
            transaction.setTransactionType("OUT");
            transaction.setReference("Sales");
            transaction.setTransactionDate(new Date());
            transactionRepository.save(transaction);

            // Calculate item price
            item.setPrice(product.getUnitPrice() * item.getQuantity());
            totalAmount += item.getPrice();
        }

        sales.setTotalAmount(totalAmount);

        return salesRepository.save(sales);
    }

    public Sales updateSales(Long id, Sales sales) {
        Sales existing = getSalesById(id);
        if (existing != null) {
            existing.setSalesCode(sales.getSalesCode());
            existing.setCustomerName(sales.getCustomerName());
            existing.setSalesDate(sales.getSalesDate());
            existing.setTotalAmount(sales.getTotalAmount());

            existing.getItems().clear();
            sales.getItems().forEach(item -> item.setSales(existing));
            existing.getItems().addAll(sales.getItems());

            return salesRepository.save(existing);
        }
        return null;
    }

    public void deleteSales(Long id) {
        salesRepository.deleteById(id);
    }

    public Sales updateStatus(Long saleId, SalesStatus status) {
        Sales sale = salesRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        sale.setStatus(status);
        return salesRepository.save(sale);
    }

    // Total Revenue
    public double getTotalRevenue() {
        return salesRepository.findAll()
                .stream()
                .mapToDouble(Sales::getTotalAmount)
                .sum();
    }

    @Transactional
    public Sales checkout(CustomerCheckoutRequest request) {
        Sales sale = new Sales();
        sale.setSalesDate(new Date());
        sale.setStatus(SalesStatus.PENDING);

        // Set customer
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        sale.setCustomer(customer);

        double totalAmount = 0;

        for (CustomerCheckoutRequest.Item reqItem : request.getItems()) {
            Product product = productRepository.findById(reqItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + reqItem.getProductId()));

            if (product.getStockQty() < reqItem.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            // Reduce stock
            product.setStockQty(product.getStockQty() - reqItem.getQuantity());
            productRepository.save(product);

            // Create stock transaction
            StockTransaction transaction = new StockTransaction();
            transaction.setProduct(product);
            transaction.setQuantity(reqItem.getQuantity());
            transaction.setTransactionType("OUT");
            transaction.setReference("Customer Checkout");
            transaction.setTransactionDate(new Date());
            transactionRepository.save(transaction);

            // Create SalesItem
            SalesItem item = new SalesItem();
            item.setProduct(product);
            item.setQuantity(reqItem.getQuantity());
            item.setPrice(product.getUnitPrice() * reqItem.getQuantity());
            item.setSales(sale);

            sale.getItems().add(item);
            totalAmount += item.getPrice();
        }

        sale.setTotalAmount(totalAmount);
        return salesRepository.save(sale);
    }

}
