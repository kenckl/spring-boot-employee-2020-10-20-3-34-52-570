package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyRepository1 companyRepository1;

    @Autowired
    private EmployeeRepository1 employeeRepository1;
    //add employeeService


    public List<Company> getAllCompanies(){
        return companyRepository.findAllCompanies();
    }

    public Company createCompany(Company company) {
        return companyRepository.addCompany(company);
    }

    public Company findCompanyById(String id) {
        return companyRepository.findCompanyById(id);
    }

    public Company updateCompanyById(String id, Company company) {
        return companyRepository.updateCompanyById(id, company);
    }


    public void deleteCompanyById(String id) {
        companyRepository.deleteCompanyById(id);
    }

    public List<Company> getCompanyByPage(Integer page, Integer pageSize) {
        return companyRepository.getCompanyByPage(page,pageSize);
    }

    public List<Employee> getEmployeesByCompanyId(String companyId) {
        return companyRepository.getEmployeesByCompanyId(companyId);
    }

    public List<Employee> getCompanyEmployee(String companyId){
        return employeeRepository1.findByCompanyId(companyId);
    }
}
