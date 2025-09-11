package com.sadiar.erp.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "vendorpayment")
public class VendorPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    private Date paymentDate;
    private double amount;
    private String status; // PENDING, PAID

    // getters & setters
}
