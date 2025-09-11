package com.sadiar.erp.repository;

import com.sadiar.erp.dto.CustomerSalesReportDTO;
import com.sadiar.erp.dto.SalesReportDTO;
import com.sadiar.erp.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ISalesRepo extends JpaRepository<Sales,Long> {

    @Query("SELECT new com.sadiar.erp.dto.SalesReportDTO(s.salesDate, COUNT(s), SUM(s.totalAmount)) " +
            "FROM Sales s " +
            "WHERE s.salesDate BETWEEN :from AND :to " +
            "GROUP BY s.salesDate " +
            "ORDER BY s.salesDate ASC")
    List<SalesReportDTO> getSalesReport(Date from, Date to);


    @Query("SELECT new com.sadiar.erp.dto.CustomerSalesReportDTO(" +
            "c.id, c.name, COUNT(s), SUM(s.totalAmount)) " +
            "FROM Sales s JOIN s.customer c " +
            "WHERE s.salesDate BETWEEN :from AND :to " +
            "GROUP BY c.id, c.name " +
            "ORDER BY SUM(s.totalAmount) DESC")
    List<CustomerSalesReportDTO> getCustomerWiseReport(Date from, Date to);

}
