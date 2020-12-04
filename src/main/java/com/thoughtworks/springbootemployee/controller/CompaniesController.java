package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private CompanyService companyService;
    private final CompanyMapper comapnyMapper;
    private final EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper){
        this.companyService = companyService;
        this.comapnyMapper = companyMapper;
    }

    @GetMapping
    public List<CompanyResponse> getCompanyList(){
        List<Company> companies = companyService.getAllCompanies();
        return companies.stream().map(comapnyMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    public CompanyResponse createCompany(@RequestBody CompanyRequest companyRequest){
        Company company = companyService.addCompany(comapnyMapper.toEntity(companyRequest));
        return comapnyMapper.toResponse(company);
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
