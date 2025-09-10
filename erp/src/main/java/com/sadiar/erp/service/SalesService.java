package com.sadiar.erp.service;

import com.sadiar.erp.entity.Sales;
import com.sadiar.erp.repository.ISalesItemRepo;
import com.sadiar.erp.repository.ISalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private ISalesRepo salesRepository;

    @Autowired
    private ISalesItemRepo salesItemRepository;

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales getSalesById(Long id) {
        return salesRepository.findById(id).orElse(null);
    }

    public Sales createSales(Sales sales) {
        // cascade type ALL ensures items also get saved
        sales.getItems().forEach(item -> item.setSales(sales));
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
}
