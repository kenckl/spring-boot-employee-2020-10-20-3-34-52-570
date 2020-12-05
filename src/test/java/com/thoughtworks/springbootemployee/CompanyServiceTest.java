package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
    void should_return_company_when_get_by_id_given_in_database() throws CompanyNotFoundException {
        //given
        Optional<Company> expected = Optional.of(new Company( "ABC Company"));
        when(companyRepository1.findById("1")).thenReturn(expected);

        //when
        Company actual = companyService.findCompanyById("1");

        //then
        assertEquals(expected.get(), actual);
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
    void should_return_update_company_when_update_by_id_given_in_database() throws CompanyNotFoundException {
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
        List <Company> companies = new ArrayList<>();
        companies.add(new Company("ABC Company"));
        Page<Company> expected = new PageImpl<>(companies);

        when(companyRepository1.findAll((Pageable)any())).thenReturn(expected);

        //when
        Page<Company> actualEmployees = companyService.getCompanyByPage(2, 2);

        //then
        assertEquals(expected, actualEmployees);
    }


    @Test
    void should_get_company_employees_when_get_by_company_id_given_company_id() throws CompanyNotFoundException, EmployeeNotFoundException {
        //given
        ArrayList <Employee> abcCompanyEmployees = new ArrayList<>();
        Employee employee1 = new Employee("1", "ken1", 21, "male", 10000, "1");
        Employee employee2 = new Employee("2", "ken2", 21, "male", 10000, "1");
        Employee employee3 = new Employee("3", "ken3", 21, "male", 10000, "1");
        Employee employee4 = new Employee("4", "ken4", 21, "male", 10000, "1");
        abcCompanyEmployees.add(employee1);
        abcCompanyEmployees.add(employee2);
        abcCompanyEmployees.add(employee3);
        abcCompanyEmployees.add(employee4);
        when(employeeRepository1.findByCompanyId(anyString())).thenReturn(abcCompanyEmployees);

        //when
        List<Employee> actualList = companyService.getCompanyEmployee("1");

        //then
        assertEquals(abcCompanyEmployees, actualList);
    }
}
