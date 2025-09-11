package com.sadiar.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "purchaseorderitem")
public class PurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    private Product product;

    private int quantity;
    private double unitPrice;

    // getters & setters

    public PurchaseOrderItem() {
    }

    public PurchaseOrderItem(Long id, PurchaseOrder purchaseOrder, Product product, int quantity, double unitPrice) {
        this.id = id;
        this.purchaseOrder = purchaseOrder;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
