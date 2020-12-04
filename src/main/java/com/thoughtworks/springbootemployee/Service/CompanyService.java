package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return companyRepository1.findAll();
    }

    public Company createCompany(Company company) {
        return companyRepository1.save(company);
    }

    public Company findCompanyById(String id) throws CompanyNotFoundException {
        return companyRepository1.findByCompanyId(id).orElseThrow(() -> new CompanyNotFoundException());
    }

    // handle exception if null
    // check if company.get(id) == id
    public Company updateCompanyById(String id, Company company) {
        Company company = findByCompanyId(id);
        if (companyRepository1.existsById(id))
            return companyRepository1.save(company);
        return null;
    }


    public void deleteCompanyById(String id) {
        companyRepository1.deleteById(id);
    }

    // findAll(pagable) in repository
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
