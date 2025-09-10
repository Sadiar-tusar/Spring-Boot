package com.sadiar.erp.restcontroller;

import com.sadiar.erp.entity.Product;
import com.sadiar.erp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService prodService;

    public ProductRestController(ProductService prodService) {
        this.prodService = prodService;
    }

    @GetMapping
    public List<Product> getAll() {
        return prodService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(prodService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return prodService.create(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        return prodService.update(id, p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
