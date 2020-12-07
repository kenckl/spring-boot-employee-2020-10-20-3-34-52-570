package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import com.thoughtworks.springbootemployee.Service.CompanyService;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegratonTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CompanyRepository1 companyRepository1;

    @Autowired
    private EmployeeRepository1 employeeRepository1;

    @AfterEach
    void tearDown(){
        companyRepository1.deleteAll();
        employeeRepository1.deleteAll();
    }

    @Test
    public void should_return_all_companies_when_get_all_given_companies() throws Exception {
        //given
        Company company = new Company("ABC Company");
        companyRepository1.save(company);
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("Ken", 18, "male", 100000, company.getCompanyId());
        employees.add(employeeRepository1.save(employee));

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(company.getCompanyId()))
                .andExpect(jsonPath("$[0].name").value("ABC Company"));
    }

    @Test
    public void should_return_company_when_create_given_company() throws Exception {
        //given
        String companyAsJson = "{\n" +
                "    \"name\": \"ABC Company\",\n" +
                "}";
        //when
        //then
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("ABC Company"));

        List<Company> companies = companyRepository1.findAll();
        assertEquals(1, companies.size());
        assertEquals("ABC Company", companies.get(0).getCompanyName());
    }

    @Test
    public void should_return_update_company_when_update_given_company() throws Exception {
        //given
        Company company = new Company("ABC Company");
        employeeRepository1.save(new Employee("Ken", 21, "male", 100000, company.getCompanyId()));
        companyRepository1.save(company);
        List<Employee> employees = new ArrayList<>();

        String companyAsJson = "{\n" +
                "    \"name\": \"ABC Company\",\n" +
                "}";
        //when
        //then
        mockMvc.perform(put("/companies/" + company.getCompanyId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees",hasSize(1)))
                .andExpect(jsonPath("$.companyName").value("ABC Company"));

        List<Company> companies = companyRepository1.findAll();
        Assertions.assertEquals(1, companies.size());
        assertEquals("ABC Company", companies.get(0).getCompanyName());
    }

    @Test
    public void should_delete_company_when_delete_given_company_id() throws Exception {
        //given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("Ken", 21, "male", 100000);
        employees.add(employeeRepository1.save(employee));
        Company company = new Company("ABC Company");
        companyRepository1.save(company);

        //when
        //then
        mockMvc.perform(delete("/companies/" + company.getCompanyId()))
                .andExpect(status().isOk());

        assertFalse(companyRepository1.findById(company.getCompanyId()).isPresent());
    }

    @Test
    public void should_return_company_employees_when_get_employees_by_id_given_company_id() throws Exception {
        //given
        Company company = new Company("ABC Company");
        Employee employee1 = employeeRepository1.save(new Employee("Ken1", 21, "male", 100000, company.getCompanyId()));
        Employee employee2 = employeeRepository1.save(new Employee("Ken2", 21, "male", 100000, company.getCompanyId()));
        Employee employee3 = employeeRepository1.save(new Employee("Ken3", 21, "male", 100000, company.getCompanyId()));
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        companyRepository1.save(company);
        //when
        //then
        mockMvc.perform(get("/companies/" + company.getCompanyId() + "/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.", hasSize(3)))// check hasSize()
                .andExpect(jsonPath("$[0].id").value(employee1.getId()))
                .andExpect(jsonPath("$[0].name").value("Ken1"))
                .andExpect(jsonPath("$[0].age").value(21))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(100000))
                .andExpect(jsonPath("$[1].id").value(employee2.getId()))
                .andExpect(jsonPath("$[1].name").value("Ken2"))
                .andExpect(jsonPath("$[1].age").value(21))
                .andExpect(jsonPath("$[1].gender").value("male"))
                .andExpect(jsonPath("$[1].salary").value(100000))
                .andExpect(jsonPath("$[2].id").value(employee3.getId()))
                .andExpect(jsonPath("$[2].name").value("Ken3"))
                .andExpect(jsonPath("$[2].age").value(21))
                .andExpect(jsonPath("$[2].gender").value("male"))
                .andExpect(jsonPath("$[2].salary").value(100000));
    }

    @Test
    public void should_return_limited_companies_when_get_companies_by_page_given_page_size() throws Exception {
        //given
        Employee employee1 = employeeRepository1.save(new Employee("Ken1", 21, "male", 100000));
        Employee employee2 = employeeRepository1.save(new Employee("Ken2", 21, "male", 100000));
        List<Employee> employees1 = new ArrayList<>();
        List<Employee> employees2 = new ArrayList<>();
        employees1.add(employee1);
        employees2.add(employee2);
        Company company1 = new Company("ABC Company");
        companyRepository1.save(company1);
        Company company2 = new Company("XYZ Company");
        companyRepository1.save(company2);
        Company company3 = new Company("QQQ Company");
        companyRepository1.save(company3);
        //when
        //then
        mockMvc.perform(get("/companies").param("page", "1").param("pageSize", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].employees", hasSize(3)))
                .andExpect(jsonPath("$.pageable.pageNumber").value(1))
                .andExpect(jsonPath("$[0].companyId").value(company1.getCompanyId()))
                .andExpect(jsonPath("$[0].companyName").value("ABC Company"))
                .andExpect(jsonPath("$[1].companyName").value("XYZ Company"))
                .andExpect(jsonPath("$[2].companyName").value("QQQ Company"));
    }

    @Test
    public void should_return_company_not_found_exception_when_get_employees_by_id_given_invalid_companyID() {
        //given
        EmployeeRepository1 employeeRepository1 = Mockito.mock(EmployeeRepository1.class);
        CompanyRepository1 companyRepository1 = Mockito.mock(CompanyRepository1.class);
        CompanyService companyService = new CompanyService(companyRepository1, employeeRepository1);
        Mockito.when(companyRepository1.findById(any())).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(CompanyNotFoundException.class, () -> companyService.findCompanyById("1"));
    }

    @Test
    public void should_return_company_not_found_exception_when_delete_company_given_invalid_companyID() {
        //given
        EmployeeRepository1 employeeRepository1 = Mockito.mock(EmployeeRepository1.class);
        CompanyRepository1 companyRepository1 = Mockito.mock(CompanyRepository1.class);
        CompanyService companyService = new CompanyService(companyRepository1, employeeRepository1);
        Mockito.when(companyRepository1.findById(any())).thenReturn(Optional.empty());

        //when
        //then
        assertThrows(CompanyNotFoundException.class, () -> companyService.deleteCompanyById("1"));
    }
}
