package com.sadiar.erp.restcontroller;

import com.sadiar.erp.entity.Payroll;
import com.sadiar.erp.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payrolls")
public class PayrollRestController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping
    public List<Payroll> getAll() {
        return payrollService.getAllPayrolls();
    }

    @GetMapping("/{id}")
    public Payroll getById(@PathVariable Long id) {
        return payrollService.getPayrollById(id);
    }

    @PostMapping
    public Payroll generate(@RequestBody Payroll payroll) {
        return payrollService.generatePayroll(payroll);
    }

    @PutMapping("/{id}")
    public Payroll update(@PathVariable Long id, @RequestBody Payroll payroll) {
        return payrollService.updatePayroll(id, payroll);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        payrollService.deletePayroll(id);
    }
}
