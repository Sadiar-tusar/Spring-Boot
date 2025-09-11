package com.sadiar.erp.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "stock_transactions")
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private String transactionType; // IN, OUT, ADJUSTMENT
    private String reference;       // e.g., "SalesID-1" or "Manual Adjustment"
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public StockTransaction() {
    }

    public StockTransaction(Long id, Integer quantity, String transactionType, String reference, Date transactionDate, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.transactionType = transactionType;
        this.reference = reference;
        this.transactionDate = transactionDate;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
