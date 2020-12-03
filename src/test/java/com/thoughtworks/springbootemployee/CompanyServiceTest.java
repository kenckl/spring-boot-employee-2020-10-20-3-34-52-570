package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @Mock
    CompanyRepository1 companyRepository1;

    @InjectMocks
    CompanyService companyService;

//    @Test
//    public void should_return_company_lists_when_get_companies(){
//        //given
//        List<Company> expectedCompany = asList(new Company(), new Company());
//        when(companyRepository.findAllCompanies()).thenReturn(expectedCompany);
//
//        //when
//        List<Company> actualCompany = companyService.getAllCompanies();
//
//        //given
//        assertEquals(2, actualCompany.size());
//    }
//
//    @Test
//    public void should_create_company_when_create_given_company() {
//        //given
//        Company company = new Company("1", "ABC Company");
//        when(companyRepository.addCompany(company)).thenReturn(company);
//
//        //when
//        Company actualCompany = companyService.createCompany(company);
//
//        //then
//        assertEquals("ABC Company", actualCompany.getCompanyName());
//    }
//
//    @Test
//    void should_return_company_when_get_by_company_name_given_company_id() {
//        //given
//        Company company = new Company("1", "ABC Company");
//        when(companyRepository.findCompanyById(company.getCompanyId())).thenReturn(company);
//        CompanyService service = new CompanyService(companyRepository);
//        //when
//        Company actualCompany = service.findCompanyById(company.getCompanyId());
//        //then
//        assertEquals(company.getCompanyId(), actualCompany.getCompanyId());
//    }
//
//    @Test
//    void should_update_company_when_update_company_by_id_given_company_id() {
//        //given
//        Company oldCompany = new Company("1", "ABC Company");
//        Company newCompany = new Company("1", "XYZ Company");
//        when(companyRepository.updateCompanyById(oldCompany.getCompanyId(), newCompany)).thenReturn(newCompany);
//        CompanyService service = new CompanyService(companyRepository);
//
//        //when
//        Company actualCompany = service.updateCompanyById(oldCompany.getCompanyId(), newCompany);
//
//        //then
//        assertEquals("XYZ Company", actualCompany.getCompanyName());
//    }
//
//    @Test
//    void should_delete_company_when_delete_company_by_id_given_company() {
//        //given
//        Company company = new Company("1", "ABC Company");
//        CompanyService service = new CompanyService(companyRepository);
//
//        //when
//        service.deleteCompanyById(company.getCompanyId());
//
//        //then
//        verify(companyRepository, times(1)).deleteCompanyById(company.getCompanyId());
//    }
//
//    @Test
//    void should_return_part_of_company_list_when_getByPage_given_company_request() {
//        //given
//        List<Company> companies = asList(new Company("1", "AAA Company"),
//                new Company("2", "BBB Company"),
//                new Company("3", "CCC Company"),
//                new Company("4", "DDD Company"),
//                new Company("5", "EEE Company"));
//        when(companyRepository.getCompanyByPage(1, 5)).thenReturn(companies);
//        CompanyService companyService = new CompanyService(companyRepository);
//
//        //when
//        List<Company> actualCompany = companyService.getCompanyByPage(1, 5);
//
//        //then
//        assertEquals(5, actualCompany.size());
//    }
//
//    @Test
//    void should_return_employee_list_when_search_in_company_given_employee_and_company() {
//        //given
//        Company company = new Company("1", "ABC Company");
//        Employee employee = new Employee("1", "Ken", 18, "Male", 100000, "1");
//        when(companyRepository.getEmployeesByCompanyId(company.getCompanyId())).thenReturn(Collections.singletonList(employee));
//        CompanyService companyService = new CompanyService(companyRepository);
//
//        //when
//        List<Employee> actualEmployee = companyService.getEmployeesByCompanyId(company.getCompanyId());
//
//        //then
//        assertTrue(actualEmployee.contains(employee));
//    }


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
        Optional<Company> expected = Optional.of(new Company("1", "ABC Company"));
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

        //given
        Company expected = new Company("1", "ABC Company");

        //when
        companyService.deleteCompanyById("1");

        //then
        verify(companyRepository1, times(1)).deleteById("1");
    }
}
