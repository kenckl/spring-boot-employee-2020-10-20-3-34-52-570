package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.Model.Employee;

import java.util.List;

public class CompanyResponse {
    private String companyId;
    private String companyName;

    public CompanyResponse(String companyId, String companyName, List<Employee> employees){
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public CompanyResponse(){}

    public String getCompanyId(){
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
