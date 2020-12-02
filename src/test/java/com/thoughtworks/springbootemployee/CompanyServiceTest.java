package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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

    @Test
    public void should_create_company_when_create_given_company() {
        //given
        Company company = new Company(1, "ABC Company");
        when(companyRepository.addCompany(company)).thenReturn(company);

        //when
        Company actualCompany = companyService.createCompany(company);

        //then
        assertEquals("ABC Company", actualCompany.getCompanyName());
    }

    @Test
    void should_return_company_when_get_by_company_name_given_company_id() {
        //given
        Company company = new Company(1, "ABC Company");
        when(companyRepository.findCompanyById(company.getCompanyId())).thenReturn(company);
        CompanyService service = new CompanyService(companyRepository);
        //when
        Company actual = service.findCompanyById(company.getCompanyId());
        //then
        assertEquals(company.getCompanyId(), actual.getCompanyId());
    }


}
