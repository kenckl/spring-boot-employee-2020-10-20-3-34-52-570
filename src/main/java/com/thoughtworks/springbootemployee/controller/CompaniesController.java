package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper){
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<CompanyResponse> getCompanyList(){
        List<Company> companies = companyService.getAllCompanies();
        return companies.stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@RequestBody CompanyRequest companyRequest){
        Company company = companyMapper.toEntity(companyRequest);
        companyService.createCompany(company);
        return companyMapper.toResponse(company);
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getCompanyByCompanyId(@PathVariable String companyId) throws CompanyNotFoundException {
        Company company = companyService.findCompanyById(companyId);
        return companyMapper.toResponse(company);
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
    public List<EmployeeResponse> getCompanyEmployees(@PathVariable("id") String id){
        List<Employee> employees = companyService.getCompanyEmployee(id);
        return employees.stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }
}
