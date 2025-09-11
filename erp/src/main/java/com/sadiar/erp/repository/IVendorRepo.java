package com.sadiar.erp.repository;

import com.sadiar.erp.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVendorRepo extends JpaRepository<Vendor, Long> {
}
