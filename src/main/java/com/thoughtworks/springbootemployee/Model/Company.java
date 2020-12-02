package com.thoughtworks.springbootemployee.Model;

public class Company {
    private Integer companyId;
    private String companyName;

    public Company(Integer companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }
}
