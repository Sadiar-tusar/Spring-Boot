package com.sadiar.erp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String salesCode;
    private String customerName;
    private Date salesDate;
    private Double totalAmount;

    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL)
    private List<SalesItem> items = new ArrayList<>();

    public Sales() {
    }

    public Sales(Long id, String salesCode, String customerName, Date salesDate, Double totalAmount, List<SalesItem> items) {
        this.id = id;
        this.salesCode = salesCode;
        this.customerName = customerName;
        this.salesDate = salesDate;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesCode() {
        return salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<SalesItem> getItems() {
        return items;
    }

    public void setItems(List<SalesItem> items) {
        this.items = items;
    }
}
