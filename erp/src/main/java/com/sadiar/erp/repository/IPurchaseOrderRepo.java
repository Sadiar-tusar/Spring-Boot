package com.sadiar.erp.repository;

import com.sadiar.erp.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseOrderRepo extends JpaRepository<PurchaseOrder,Long> {
}
