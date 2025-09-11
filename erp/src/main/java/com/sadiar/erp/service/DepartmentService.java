package com.sadiar.erp.service;

import com.sadiar.erp.entity.Department;
import com.sadiar.erp.repository.IDepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final IDepartmentRepo deptRepo;

    public DepartmentService(IDepartmentRepo deptRepo) {
        this.deptRepo = deptRepo;
    }

    public List<Department> getAllDepartments() {
        return deptRepo.findAll();
    }

    public Department getDepartmentById(Long id) {
        return deptRepo.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department createDepartment(Department dept) {
        return deptRepo.save(dept);
    }

    public Department updateDepartment(Long id, Department dept) {
        return deptRepo.findById(id).map(existing -> {
            existing.setName(dept.getName());
            return deptRepo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public void deleteDepartment(Long id) {
        deptRepo.deleteById(id);
    }
}
