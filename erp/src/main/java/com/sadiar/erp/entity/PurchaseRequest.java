package com.sadiar.erp.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "requestpurchase")
public class PurchaseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date requestDate;
    private String requestedBy; // Employee name or ID
    private String status; // PENDING, APPROVED, REJECTED

    @OneToMany(mappedBy = "purchaseRequest", cascade = CascadeType.ALL)
    private List<PurchaseRequestItem> items;

    // getters & setters


    public PurchaseRequest() {
    }

    public PurchaseRequest(Long id, Date requestDate, String requestedBy, String status, List<PurchaseRequestItem> items) {
        this.id = id;
        this.requestDate = requestDate;
        this.requestedBy = requestedBy;
        this.status = status;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PurchaseRequestItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseRequestItem> items) {
        this.items = items;
    }
}
