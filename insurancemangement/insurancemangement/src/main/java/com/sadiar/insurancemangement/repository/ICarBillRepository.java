package com.sadiar.insurancemangement.repository;

import com.sadiar.insurancemangement.entity.CarBill;
import com.sadiar.insurancemangement.entity.FireBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarBillRepository extends JpaRepository<CarBill,Integer> {

    // Custom query to find bills by policyholder in the associated policy
    @Query("SELECT b FROM CarBill b  WHERE LOWER(b.carPolicy.policyholder) LIKE LOWER(CONCAT('%', :policyholder, '%'))")
    List<CarBill> findBillsByPolicyholder(@Param("policyholder") String policyholder);

    List<CarBill> findByCarPolicyId(int carPolicyId);



    // Custom query to find bills by policy ID
    @Query("SELECT b FROM CarBill b WHERE b.carPolicy.id = :carPolicyId")
    List<CarBill> findBillsByPolicyId(@Param("carPolicyId") int carPolicyId);
}
