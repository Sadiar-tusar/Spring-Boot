package com.sadiar.erp.restcontroller;

import com.sadiar.erp.entity.Attendence;
import com.sadiar.erp.service.AttendenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendenceRestController {

    private final AttendenceService attService;

    public AttendenceRestController(AttendenceService attService) {
        this.attService = attService;
    }

    @GetMapping
    public List<Attendence> getAll() {
        return attService.getAll();
    }

    @GetMapping("/employee/{empId}")
    public List<Attendence> getByEmployee(@PathVariable Long empId) {
        return attService.getByEmployee(empId);
    }

    @PostMapping
    public Attendence create(@RequestBody Attendence att) {
        return attService.create(att);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
