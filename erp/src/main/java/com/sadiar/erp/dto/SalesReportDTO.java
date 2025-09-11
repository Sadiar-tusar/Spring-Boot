package com.sadiar.erp.dto;

import java.util.Date;

public class SalesReportDTO {

    private Date salesDate;
    private long totalSales;
    private double totalRevenue;

    public SalesReportDTO(Date salesDate, long totalSales, double totalRevenue) {
        this.salesDate = salesDate;
        this.totalSales = totalSales;
        this.totalRevenue = totalRevenue;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public long getTotalSales() {
        return totalSales;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}
