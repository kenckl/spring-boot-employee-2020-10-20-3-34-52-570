package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CompanyServiceTest {
    CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
    CompanyService companyService = new CompanyService(companyRepository);

    @Test
    public void should_return_company_lists_when_get_companies(){
        //given
        List<Company> expectedCompany = asList(new Company(), new Company());
        when(companyRepository.findAllCompanies()).thenReturn(expectedCompany);

        //when
        List<Company> actualCompany = companyService.getAllCompanies();

        //given
        assertEquals(2, actualCompany.size());
    }
}
