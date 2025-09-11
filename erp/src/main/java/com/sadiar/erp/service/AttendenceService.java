package com.sadiar.erp.service;

import com.sadiar.erp.entity.Attendence;
import com.sadiar.erp.entity.Employee;
import com.sadiar.erp.repository.IAttendenceRepo;
import com.sadiar.erp.repository.IEmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendenceService {

    private final IAttendenceRepo attRepo;
    private final IEmployeeRepo employeeRepo;

    public AttendenceService(IAttendenceRepo attRepo, IEmployeeRepo employeeRepo) {
        this.attRepo = attRepo;
        this.employeeRepo = employeeRepo;
    }

    public List<Attendence> getAll() {
        return attRepo.findAll();
    }

    public List<Attendence> getByEmployee(Long empId) {
        return attRepo.findByEmployeeId(empId);
    }

    public Attendence create(Attendence att) {
        return attRepo.save(att);
    }

//    public Attendence create(Attendence att) {
//        if (att.getEmployee() != null && att.getEmployee().getId() != null) {
//            Employee emp = employeeRepo.findById(att.getEmployee().getId())
//                    .orElseThrow(() -> new RuntimeException("Employee not found"));
//            att.setEmployee(emp);  // set managed entity
//        }
//        return attRepo.save(att);
//    }

    public void delete(Long id) {
        attRepo.deleteById(id);
    }
}
