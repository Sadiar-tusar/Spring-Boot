package com.sadiar.erp.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchaseorder")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private Date orderDate;
    private String status; // PENDING, PARTIAL, RECEIVED

    @ManyToOne
    private Vendor vendor;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<PurchaseOrderItem> items;

    // getters & setters
}
