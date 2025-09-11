package com.sadiar.erp.repository;

import com.sadiar.erp.entity.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesItemRepo extends JpaRepository<SalesItem,Long> {
}
