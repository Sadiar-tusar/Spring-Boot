package com.sadiar.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;
    private Double baseSalary;
    private Double deductions;
    private Double overtime;
    private Double netPay;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Payroll() {
    }

    public Payroll(Long id, String month, Double baseSalary, Double deductions, Double overtime, Double netPay, Employee employee) {
        this.id = id;
        this.month = month;
        this.baseSalary = baseSalary;
        this.deductions = deductions;
        this.overtime = overtime;
        this.netPay = netPay;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public Double getOvertime() {
        return overtime;
    }

    public void setOvertime(Double overtime) {
        this.overtime = overtime;
    }

    public Double getNetPay() {
        return netPay;
    }

    public void setNetPay(Double netPay) {
        this.netPay = netPay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
