package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    private final CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanyList(){
        return companyService.getAllCompanies();
    }

    @PostMapping
    public Company createCompany(@RequestBody Company createCompany){
        return companyService.createCompany(createCompany);
    }

    @GetMapping("/{companyId}")
    public Company getCompanyById(@PathVariable int companyId) {
        return companyService.findCompanyById(companyId);
    }
}
