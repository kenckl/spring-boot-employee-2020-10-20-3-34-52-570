package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    CompanyRepository1 companyRepository1;

    @Mock
    EmployeeRepository1 employeeRepository1;

    @InjectMocks
    CompanyService companyService;

    @Test
    void should_return_all_when_get_all_given_some_companies_in_database(){
        //given
        final List<Company> expected = Arrays.asList(new Company("1", "ABC Company"));
        when(companyRepository1.findAll()).thenReturn(expected);

        //when
        final List<Company> actual = companyService.getAllCompanies();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_company_when_get_by_id_given_in_database(){
        //given
        Company expected = Optional.of(new Company("1", "ABC Company"));
        when(companyRepository1.findById("1")).thenReturn(expected);

        //when
        Optional<Company> actual = companyService.findCompanyById("1");

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_company_when_save_given_in_database(){
        //given
        Company expected = new Company("1", "ABC Company");
        when(companyRepository1.save(expected)).thenReturn(expected);

        //when
        Company actual = companyService.createCompany(expected);

        //then
        assertEquals(expected.getCompanyId(), actual.getCompanyId());
        assertEquals(expected.getCompanyName(), actual.getCompanyName());
    }

    @Test
    void should_return_update_company_when_update_by_id_given_in_database(){
        //given
        Company expected = new Company("1", "ABC Company");
        when(companyRepository1.existsById("1")).thenReturn(true);
        when(companyRepository1.save(expected)).thenReturn(expected);

        //when
        Company actual = companyService.updateCompanyById("1", expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_delete_company_when_delete_by_id_given_in_database(){

        // use mockito test
        //given
        Company expected = new Company("1", "ABC Company");

        //when
        companyService.deleteCompanyById("1");

        //then
        verify(companyRepository1, times(1)).deleteById("1");
    }

    @Test
    void should_get_limited_company_when_get_employee_wth_page_size_given_in_database(){
        //given
        ArrayList<Company> companies = new ArrayList<>();
        Company company1 = new Company("1", "AAA Company");
        Company company2 = new Company("2", "BBB Company");
        Company company3 = new Company("3", "CCC Company");
        Company company4 = new Company("4", "DDD Company");
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);
        companies.add(company4);
        when(companyRepository1.findAll()).thenReturn(companies);

        //when
        List<Company> actual = companyService.getCompanyByPage(0,3);

        //then
        assertEquals(3, actual.size());
        assertEquals("1", actual.get(0).getCompanyId());
        assertEquals("2", actual.get(1).getCompanyId());
        assertEquals("3", actual.get(2).getCompanyId());
    }


    @Test
    void should_get_company_employees_when_get_by_company_id_given_company_id() {
        //given
        // final variable to replace company id
        ArrayList <Employee> abcCompanyEmployees = new ArrayList<>();
        Employee employee1 = new Employee("1", "ken1", 21, "male", 10000, "99");
        Employee employee2 = new Employee("2", "ken2", 21, "male", 10000, "99");
        Employee employee3 = new Employee("3", "ken3", 21, "male", 10000, "99");
        Employee employee4 = new Employee("4", "ken4", 21, "male", 10000, "99");
        abcCompanyEmployees.add(employee1);
        abcCompanyEmployees.add(employee2);
        abcCompanyEmployees.add(employee3);
        abcCompanyEmployees.add(employee4);
        when(employeeRepository1.findByCompanyId(anyString())).thenReturn(abcCompanyEmployees);

        //when
        List<Employee> actualList = companyService. getCompanyEmployee("99");

        //then
        assertEquals(abcCompanyEmployees, actualList);
    }
}
