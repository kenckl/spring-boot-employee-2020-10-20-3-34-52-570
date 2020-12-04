package com.thoughtworks.springbootemployee.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Company {

    @MongoId(FieldType.OBJECT_ID)
    private String companyId;
    private String companyName;
    private List<Employee> employees;
    private Integer totalEmployee;

    public Company(String companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company(String companyName, Integer totalEmployee, List<Employee> employees) {
        this.companyName = companyName;
        this.totalEmployee = totalEmployee;
        this.employees = employees;
    }

    public Company(String companyId, String companyName, Integer totalEmployee, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.totalEmployee = totalEmployee;
        this.employees = employees;
    }

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public Integer getTotalEmployee() {
        return totalEmployee;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
