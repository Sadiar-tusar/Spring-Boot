package com.sadiar.erp.service;

import com.sadiar.erp.entity.Attendence;
import com.sadiar.erp.repository.IAttendenceRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendenceService {

    private final IAttendenceRepo attRepo;

    public AttendenceService(IAttendenceRepo attRepo) {
        this.attRepo = attRepo;
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

    public void delete(Long id) {
        attRepo.deleteById(id);
    }
}
