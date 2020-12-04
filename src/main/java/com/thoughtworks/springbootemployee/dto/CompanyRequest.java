package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.Model.Employee;

import java.util.List;

public class CompanyRequest {
    private String companyName;
    private List<Employee> employees;

    public CompanyRequest(String companyName, List<Employee> employees){
        this.companyName = companyName;
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
