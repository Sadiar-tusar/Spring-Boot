package com.sadiar.erp.repository;

import com.sadiar.erp.entity.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAttendenceRepo extends JpaRepository<Attendence,Long> {
    List<Attendence> findByEmployeeId(Long empId);
}
