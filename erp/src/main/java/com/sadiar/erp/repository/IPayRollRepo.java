package com.sadiar.erp.repository;

import com.sadiar.erp.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPayRollRepo extends JpaRepository<Payroll, Long> {

    List<Payroll> findByEmployeeId(Long employeeId);
}
