package com.sadiar.erp.restcontroller;

import com.sadiar.erp.entity.Sales;
import com.sadiar.erp.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Sales getSalesById(@PathVariable Long id) {
        return salesService.getSalesById(id);
    }

    @PostMapping
    public Sales createSales(@RequestBody Sales sales) {
        return salesService.createSales(sales);
    }

    @PutMapping("/{id}")
    public Sales updateSales(@PathVariable Long id, @RequestBody Sales sales) {
        return salesService.updateSales(id, sales);
    }

    @DeleteMapping("/{id}")
    public void deleteSales(@PathVariable Long id) {
        salesService.deleteSales(id);
    }
}
