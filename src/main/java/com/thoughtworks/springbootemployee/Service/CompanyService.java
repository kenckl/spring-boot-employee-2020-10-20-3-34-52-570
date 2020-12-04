package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository1 companyRepository1;

    @Autowired
    private EmployeeRepository1 employeeRepository1;

    public List<Company> getAllCompanies(){
        final Iterable<Company> allCompanyId = companyRepository1.findAllById(new ArrayList<>());
        return companyRepository1.findAll();
    }

    public Company createCompany(Company company) {
        return companyRepository1.save(company);
    }

    public Optional<Company> findCompanyById(String id) {
        return companyRepository1.findCompanyById(id);
    }

    public Company updateCompanyById(String id, Company company) {
        if (companyRepository1.existsById(id))
            return companyRepository1.save(company);
        return null;
    }


    public void deleteCompanyById(String id) {
        companyRepository1.deleteById(id);
    }

    public List<Company> getCompanyByPage(Integer page, Integer pageSize) {
        return companyRepository1.findAll().stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> getCompanyEmployee(String companyId){
        return employeeRepository1.findByCompanyId(companyId);
    }

}
