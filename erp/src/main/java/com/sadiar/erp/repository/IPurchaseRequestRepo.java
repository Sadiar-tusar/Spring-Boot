package com.sadiar.erp.repository;

import com.sadiar.erp.entity.PurchaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseRequestRepo extends JpaRepository<PurchaseRequest,Long> {
}
