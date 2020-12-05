package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import org.springframework.beans.BeanUtils;

public class CompanyMapper {
    public Company toEntity(CompanyRequest companyRequest){
        Company company = new Company();

        BeanUtils.copyProperties(companyRequest, company);

        return company;
    }

    public CompanyResponse toResponse(Company company){
        CompanyResponse companyResponse = new CompanyResponse();

        BeanUtils.copyProperties(company, companyResponse);

        return companyResponse;
    }
}
