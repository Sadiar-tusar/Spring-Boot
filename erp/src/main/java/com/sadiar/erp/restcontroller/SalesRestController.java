package com.sadiar.erp.restcontroller;

import com.sadiar.erp.dto.CustomerCheckoutRequest;
import com.sadiar.erp.entity.Sales;
import com.sadiar.erp.entity.SalesStatus;
import com.sadiar.erp.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesRestController {

    @Autowired
    private SalesService salesService;

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSalesById(@PathVariable Long id) {
        Sales sale = salesService.getSalesById(id);
        if (sale == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sale);
    }

    @PostMapping
    public ResponseEntity<Sales> createSales(@RequestBody Sales sales) {
        return ResponseEntity.ok(salesService.createSales(sales));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sales> updateSales(@PathVariable Long id, @RequestBody Sales sales) {
        Sales updated = salesService.updateSales(id, sales);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSales(@PathVariable Long id) {
        salesService.deleteSales(id);
        return ResponseEntity.noContent().build();
    }

    // Update Sales Status
    @PutMapping("/{id}/status")
    public ResponseEntity<Sales> updateStatus(@PathVariable Long id,
                                              @RequestParam SalesStatus status) {
        try {
            Sales updatedSale = salesService.updateStatus(id, status);
            return ResponseEntity.ok(updatedSale);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/report/total-revenue")
    public ResponseEntity<Double> getTotalRevenue() {
        double revenue = salesService.getTotalRevenue();
        return ResponseEntity.ok(revenue);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Sales> checkout(@RequestBody CustomerCheckoutRequest request) {
        try {
            Sales sale = salesService.checkout(request);
            return ResponseEntity.ok(sale);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
