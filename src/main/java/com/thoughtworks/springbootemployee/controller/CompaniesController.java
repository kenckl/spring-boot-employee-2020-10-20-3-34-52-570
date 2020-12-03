package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    private final CompanyService companyService;
    private final List<Company> companies = new ArrayList<>();

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanyList(){
        return companyService.getAllCompanies();
    }

    @PostMapping
    public Company createCompany(@RequestBody Company createCompany){
        companies.add(createCompany);
        return createCompany;
    }

    @GetMapping("/{companyId}")
    public Company getCompanyByCompanyId(@PathVariable int companyId) {
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{companyId}")
    public Company updateCompanyByCompanyId(@PathVariable int companyId, @RequestBody Company companyUpdate) {
        companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId)).findFirst()
                .ifPresent(comapny -> {
                    companies.remove(comapny);
                    companies.add(companyUpdate);
                });
        return companyUpdate;
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompanyByCompanyId(@PathVariable String companyId){
        companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .ifPresent(companies::remove);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompanyByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return companies.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
