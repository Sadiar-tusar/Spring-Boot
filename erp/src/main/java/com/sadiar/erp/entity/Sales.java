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

    @Enumerated(EnumType.STRING)
    private SalesStatus status = SalesStatus.PENDING; // default PENDING

    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL)
    private List<SalesItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Sales() {
    }

    public Sales(Long id, String salesCode, String customerName, Date salesDate, Double totalAmount, SalesStatus status, List<SalesItem> items, Customer customer) {
        this.id = id;
        this.salesCode = salesCode;
        this.customerName = customerName;
        this.salesDate = salesDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.items = items;
        this.customer = customer;
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

    public SalesStatus getStatus() {
        return status;
    }

    public void setStatus(SalesStatus status) {
        this.status = status;
    }

    public List<SalesItem> getItems() {
        return items;
    }

    public void setItems(List<SalesItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
