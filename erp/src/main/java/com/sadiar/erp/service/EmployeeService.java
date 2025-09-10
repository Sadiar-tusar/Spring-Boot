package com.sadiar.erp.service;

import com.sadiar.erp.entity.Employee;
import com.sadiar.erp.repository.IEmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final IEmployeeRepo empRepo;

    public EmployeeService(IEmployeeRepo empRepo) {
        this.empRepo = empRepo;
    }

    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return empRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee createEmployee(Employee emp) {
        return empRepo.save(emp);
    }

    public Employee updateEmployee(Long id, Employee emp) {
        return empRepo.findById(id).map(existing -> {
            existing.setDesignation(emp.getDesignation());
            existing.setSalary(emp.getSalary());
            existing.setHireDate(emp.getHireDate());
            existing.setUser(emp.getUser());
            return empRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        empRepo.deleteById(id);
    }
}
