package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public List<Company> findAllCompanies() {
        return companies;
    }

    public Company addCompany(Company company) {
        companies.add(company);
        return company;
    }

    public Company findCompanyById(Integer id) {
        return companies.stream()
                .filter(company -> company.getCompanyId() == id)
                .findFirst()
                .orElse(null);
    }

    public Company updateCompanyById(Integer id, Company newCompany){
        companies.stream()
                .filter(company -> company.getCompanyId() == id)
                .findFirst()
                .ifPresent(company -> {
                    companies.remove(company);
                    companies.add(newCompany);
                });
        return newCompany;
    }

    public void deleteCompanyById(Integer id) {
        companies.stream()
                .filter(company -> company.getCompanyId() == id)
                .findFirst()
                .ifPresent(companies::remove);
    }
}
