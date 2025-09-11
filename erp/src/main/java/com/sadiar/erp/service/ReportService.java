package com.sadiar.erp.service;

import com.sadiar.erp.dto.CustomerSalesReportDTO;
import com.sadiar.erp.dto.SalesReportDTO;
import com.sadiar.erp.repository.ISalesRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    private final ISalesRepo salesRepository;

    public ReportService(ISalesRepo salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<SalesReportDTO> getSalesReport(Date from, Date to) {
        return salesRepository.getSalesReport(from, to);
    }

    // ReportService.java
    public List<CustomerSalesReportDTO> getCustomerWiseReport(Date from, Date to) {
        return salesRepository.getCustomerWiseReport(from, to);
    }

}
