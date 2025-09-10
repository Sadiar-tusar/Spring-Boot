package com.sadiar.erp.service;

import com.sadiar.erp.entity.Payroll;
import com.sadiar.erp.repository.IPayRollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private IPayRollRepo payrollRepository;

    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    public Payroll getPayrollById(Long id) {
        return payrollRepository.findById(id).orElse(null);
    }

    public Payroll generatePayroll(Payroll payroll) {
        double netPay = payroll.getBaseSalary() + payroll.getOvertime() - payroll.getDeductions();
        payroll.setNetPay(netPay);
        return payrollRepository.save(payroll);
    }

    public Payroll updatePayroll(Long id, Payroll payroll) {
        Payroll existing = getPayrollById(id);
        if (existing != null) {
            existing.setMonth(payroll.getMonth());
            existing.setBaseSalary(payroll.getBaseSalary());
            existing.setDeductions(payroll.getDeductions());
            existing.setOvertime(payroll.getOvertime());
            existing.setNetPay(
                    payroll.getBaseSalary() + payroll.getOvertime() - payroll.getDeductions()
            );
            existing.setEmployee(payroll.getEmployee());
            return payrollRepository.save(existing);
        }
        return null;
    }

    public void deletePayroll(Long id) {
        payrollRepository.deleteById(id);
    }
}
