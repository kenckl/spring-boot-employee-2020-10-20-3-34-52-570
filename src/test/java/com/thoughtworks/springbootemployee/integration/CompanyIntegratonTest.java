package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.Model.Company;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.CompanyRepository1;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
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
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("Ken", 18, "male", 100000);
        employees.add(employeeRepository1.save(employee));
        Company company = new Company("ABC Company", 1, employees);
        companyRepository1.save(company);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(company.getCompanyId()))
                .andExpect(jsonPath("$[0].name").value("ABC Company"))
                .andExpect(jsonPath("$[0].totalEmployee").value(1))
                .andExpect(jsonPath("$[0].employees[0].id").value(employees.get(0).getId()))
                .andExpect(jsonPath("$[0].employees[0].name").value("Ken"))
                .andExpect(jsonPath("$[0].employees[0].age").value(18))
                .andExpect(jsonPath("$[0].employees[0].gender").value("male"))
                .andExpect(jsonPath("$[0].employees[0].salary").value(100000));
    }

    @Test
    public void should_return_company_when_create_given_company() throws Exception {
        //given
        Employee employee = employeeRepository1.save(new Employee("Ken", 18, "male", 100000));
        String companyAsJson = "{\n" +
                "    \"name\": \"ABC Company\",\n" +
                "    \"totalEmployee\": 1,\n" +
                "     " + employee.getJSON() + "\n" +
                "    ]\n" +
                "}";
        //when
        //then
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(companyAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("ABC Company"))
                .andExpect(jsonPath("$.totalEmployee").value(1));
    }

    @Test
    public void should_return_update_company_when_update_given_company() throws Exception {
        //given
        Employee employee = employeeRepository1.save(new Employee("Ken", 18, "male", 100000));
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Company company = new Company("ABC Company", 1, employees);
        companyRepository1.save(company);

        String companyAsJson = "{\n" +
                "    \"name\": \"ABC Company\",\n" +
                "    \"totalEmployee\": 1,\n" +
                "    \"employees\": [\n" +
                "     " + employee.getJSON() + "\n" +
                "    ]\n" +
                "}";
        //when
        //then
        mockMvc.perform(put("/companies/" + company.getCompanyId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(company.getCompanyId()))
                .andExpect(jsonPath("$.name").value("ABC Company"))
                .andExpect(jsonPath("$.totalEmployee").value(1))
                .andExpect(jsonPath("$.employees[0].id").value(employee.getId()))
                .andExpect(jsonPath("$.employees[0].name").value("Ken"))
                .andExpect(jsonPath("$.employees[0].age").value(18))
                .andExpect(jsonPath("$.employees[0].gender").value("male"))
                .andExpect(jsonPath("$.employees[0].salary").value(100000));
    }

    @Test
    public void should_delete_company_when_delete_given_company_id() throws Exception {
        //given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("Ken", 18, "male", 100000);
        employees.add(employeeRepository1.save(employee));
        Company company = new Company("ABC Company", 1, employees);
        companyRepository1.save(company);

        //when
        //then
        mockMvc.perform(delete("/companies/" + company.getCompanyId()))
                .andExpect(status().isOk());
        assertFalse(companyRepository1.findById(company.getCompanyId()).isPresent());
    }

}
