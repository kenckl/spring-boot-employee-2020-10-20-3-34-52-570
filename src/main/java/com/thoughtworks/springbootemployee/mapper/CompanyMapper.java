package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import org.springframework.beans.BeanUtils;

public class CompanyMapper {
    public Company toEntity(CompanyRequest companyRequest){
        Company company = new Company();

//        employee.setName(employeeRequest.getName());
//        employee.setAge(employeeRequest.getAge());
//        employee.setGender(employeeRequest.getGender());
//        employee.setSalary(employeeRequest.getSalary());

        BeanUtils.copyProperties(companyRequest, company);

        return company;
    }

    public CompanyResponse toResponse(Company company){
        CompanyResponse companyResponse = new CompanyResponse();

//        employeeResponse.setId(employee.getId());
//        employeeResponse.setName(employee.getName());
//        employeeResponse.setAge(employee.getAge());
//        employeeResponse.setGender(employee.getGender());
//        employeeResponse.setSalary(employee.getSalary());

        BeanUtils.copyProperties(company, companyResponse);

        return companyResponse;
    }
}
