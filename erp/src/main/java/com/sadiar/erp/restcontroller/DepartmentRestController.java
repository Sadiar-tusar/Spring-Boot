package com.sadiar.erp.restcontroller;

import com.sadiar.erp.entity.Department;
import com.sadiar.erp.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

    private final DepartmentService deptService;

    public DepartmentRestController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    @GetMapping
    public List<Department> getAll() {
        return deptService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(deptService.getDepartmentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Department create(@RequestBody Department dept) {
        return deptService.createDepartment(dept);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department dept) {
        return deptService.updateDepartment(id, dept);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deptService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
