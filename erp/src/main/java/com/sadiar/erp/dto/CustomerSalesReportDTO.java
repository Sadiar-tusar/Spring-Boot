package com.sadiar.erp.dto;

public class CustomerSalesReportDTO {

    private Long customerId;
    private String customerName;
    private long totalSales;
    private double totalRevenue;

    public CustomerSalesReportDTO(Long customerId, String customerName, long totalSales, double totalRevenue) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalSales = totalSales;
        this.totalRevenue = totalRevenue;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public long getTotalSales() {
        return totalSales;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}
