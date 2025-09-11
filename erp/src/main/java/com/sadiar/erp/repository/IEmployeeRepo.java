package com.sadiar.erp.repository;

import com.sadiar.erp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUserId(int userId);

    @Query("SELECT js FROM Employee js WHERE js.user.email = :email")
    Optional<Employee> findByUserEmail(@Param("email") String email);

//    @Query("SELECT j FROM Employee j LEFT JOIN FETCH j.educations WHERE j.id = :id")
//    Optional<Employee> findByIdWithEducations(@Param("id") Long id);
}
