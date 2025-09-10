package com.sadiar.erp.repository;

import com.sadiar.erp.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesRepo extends JpaRepository<Sales,Long> {
}
