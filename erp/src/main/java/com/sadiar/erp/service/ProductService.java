package com.sadiar.erp.service;

import com.sadiar.erp.entity.Product;
import com.sadiar.erp.repository.IProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final IProductRepo prodRepo;

    public ProductService(IProductRepo prodRepo) {
        this.prodRepo = prodRepo;
    }

    public List<Product> getAll() {
        return prodRepo.findAll();
    }

    public Product getById(Long id) {
        return prodRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product create(Product p) {
        return prodRepo.save(p);
    }

    public Product update(Long id, Product p) {
        return prodRepo.findById(id).map(existing -> {
            existing.setName(p.getName());
            existing.setCategory(p.getCategory());
            existing.setStockQty(p.getStockQty());
            existing.setUnitPrice(p.getUnitPrice());
            return prodRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void delete(Long id) {
        prodRepo.deleteById(id);
    }
}
