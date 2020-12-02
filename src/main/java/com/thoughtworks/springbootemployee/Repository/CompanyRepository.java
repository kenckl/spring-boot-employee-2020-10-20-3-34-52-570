package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public List<Company> findAllCompanies() {
        return companies;
    }
}
