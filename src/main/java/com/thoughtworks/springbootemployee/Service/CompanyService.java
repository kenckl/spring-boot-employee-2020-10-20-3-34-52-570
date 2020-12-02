package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;

import java.util.List;

public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAllCompanies();
    }

    public Company createCompany(Company company) {
        return companyRepository.addCompany(company);
    }

    public Company findCompanyById(Integer id) {
        return companyRepository.findCompanyById(id);
    }

    public Company updateCompanyById(Integer id, Company company) {
        return companyRepository.updateCompanyById(id, company);
    }


    public void deleteCompanyById(Integer id) {
        companyRepository.deleteCompanyById(id);
    }

    public List<Company> getCompanyByPage(Integer page, Integer pageSize) {
        return companyRepository.getCompanyByPage(page,pageSize);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        return companyRepository.getEmployeesByCompanyId(companyId);
    }
}
