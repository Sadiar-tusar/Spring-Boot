package com.sadiar.erp.service;

import com.sadiar.erp.entity.Product;
import com.sadiar.erp.entity.StockTransaction;
import com.sadiar.erp.repository.IProductRepo;
import com.sadiar.erp.repository.IStockTransactionRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    private final IProductRepo prodRepo;
    private final IStockTransactionRepo transactionRepo;

    public ProductService(IProductRepo prodRepo, IStockTransactionRepo transactionRepo) {
        this.prodRepo = prodRepo;
        this.transactionRepo = transactionRepo;
    }

    public List<Product> getAll() {
        return prodRepo.findAll();
    }

    public Product getById(Long id) {
        return prodRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product create(Product p) {
        // Default reorderLevel if null
        if(p.getReorderLevel() == null) {
            p.setReorderLevel(10); // default low stock threshold
        }
        Product saved = prodRepo.save(p);

        // Initial StockTransaction entry if stockQty > 0
        if(saved.getStockQty() != null && saved.getStockQty() > 0) {
            StockTransaction transaction = new StockTransaction();
            transaction.setProduct(saved);
            transaction.setQuantity(saved.getStockQty());
            transaction.setTransactionType("IN");
            transaction.setReference("Initial Stock");
            transaction.setTransactionDate(new Date());
            transactionRepo.save(transaction);
        }

        return saved;
    }

    public Product update(Long id, Product p) {
        return prodRepo.findById(id).map(existing -> {
            existing.setName(p.getName());
            existing.setCategory(p.getCategory());
            existing.setUnitPrice(p.getUnitPrice());
            existing.setReorderLevel(p.getReorderLevel());

            // Stock update handling
            Integer oldQty = existing.getStockQty();
            Integer newQty = p.getStockQty();
            if(newQty != null && !newQty.equals(oldQty)) {
                existing.setStockQty(newQty);

                StockTransaction transaction = new StockTransaction();
                transaction.setProduct(existing);
                transaction.setQuantity(Math.abs(newQty - oldQty));
                transaction.setTransactionType(newQty > oldQty ? "IN" : "OUT");
                transaction.setReference("Manual Update");
                transaction.setTransactionDate(new Date());
                transactionRepo.save(transaction);
            }

            return prodRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void delete(Long id) {
        prodRepo.deleteById(id);
    }

    public List<Product> getLowStockProducts() {
        return prodRepo.findAll()
                .stream()
                .filter(p -> p.getStockQty() <= p.getReorderLevel())
                .toList();
    }
}
