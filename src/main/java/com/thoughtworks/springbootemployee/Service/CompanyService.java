package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository1 companyRepository1;

    private EmployeeRepository1 employeeRepository1;

    public CompanyService(CompanyRepository1 companyRepository1, EmployeeRepository1 employeeRepository1){
        this.companyRepository1 = companyRepository1;
        this.employeeRepository1 = employeeRepository1;
    }

    public List<Company> getAllCompanies(){
        return companyRepository1.findAll();
    }

    public Company createCompany(Company company) {
        return companyRepository1.save(company);
    }

    public Company findCompanyById(String id) throws CompanyNotFoundException {
        return companyRepository1.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException());
    }

    public Company updateCompanyById(String id, Company newCompany) throws CompanyNotFoundException {
        if (companyRepository1.existsById(id)){
            newCompany.setCompanyId(id);
            return companyRepository1.save(newCompany);
        }
        throw new CompanyNotFoundException();
    }


    public void deleteCompanyById(String id) throws CompanyNotFoundException {
        if (companyRepository1.existsById(id)) {
            companyRepository1.deleteById(id);
        } else
            throw new CompanyNotFoundException();
    }

    // findAll(pagable) in repository
    // check if page > 0, if not then throw exception (invalid page input)
    public Page<Company> getCompanyByPage(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return companyRepository1.findAll(pageable);
    }

    public List<Employee> getCompanyEmployee(String companyId) throws CompanyNotFoundException, EmployeeNotFoundException {
        if(!companyRepository1.existsById(companyId)){
            throw new CompanyNotFoundException();
        }
        if (employeeRepository1.findByCompanyId(companyId) != null){
            return employeeRepository1.findByCompanyId(companyId);
        }
        throw new EmployeeNotFoundException();
    }
}
