package com.sadiar.erp.restcontroller;

import com.sadiar.erp.dto.CustomerSalesReportDTO;
import com.sadiar.erp.dto.SalesReportDTO;
import com.sadiar.erp.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/sales")
    public List<SalesReportDTO > getSalesReport(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        return reportService.getSalesReport(from, to);
    }

    // ReportController.java
    @GetMapping("/sales/customer")
    public List<CustomerSalesReportDTO> getCustomerWiseReport(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        return reportService.getCustomerWiseReport(from, to);
    }

}
