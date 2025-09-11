package com.sadiar.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "purchaseRequestitem")
public class PurchaseRequestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PurchaseRequest purchaseRequest;

    @ManyToOne
    private Product product;

    private int quantity;

    // getters & setters


    public PurchaseRequestItem() {
    }

    public PurchaseRequestItem(Long id, PurchaseRequest purchaseRequest, Product product, int quantity) {
        this.id = id;
        this.purchaseRequest = purchaseRequest;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseRequest getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
