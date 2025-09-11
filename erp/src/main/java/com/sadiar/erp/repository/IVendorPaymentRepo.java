package com.sadiar.erp.repository;

import com.sadiar.erp.entity.VendorPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVendorPaymentRepo extends JpaRepository<VendorPayment, Long> {
}
