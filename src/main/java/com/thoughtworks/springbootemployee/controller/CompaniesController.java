package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private CompanyService companyService;
    private final List<Company> companies = new ArrayList<>();

    @GetMapping
    public List<Company> getCompanyList(){
        return companyService.getAllCompanies();
    }

    @PostMapping
    public Company createCompany(@RequestBody Company createCompany){
        return companyService.createCompany(createCompany);
    }

    @GetMapping("/{companyId}")
    public Optional<Company> getCompanyByCompanyId(@PathVariable String companyId) {
        return companyService.findCompanyById(companyId);
    }

    @PutMapping("/{companyId}")
    public Company updateCompanyByCompanyId(@PathVariable String companyId, @RequestBody Company companyUpdate) {
        return companyService.updateCompanyById(companyId, companyUpdate);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompanyByCompanyId(@PathVariable String companyId){
        companyService.deleteCompanyById(companyId);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompanyByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return companyService.getCompanyByPage(page, pageSize);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable("id") String id){
        return companyService.getCompanyEmployee(id);
    }
}
