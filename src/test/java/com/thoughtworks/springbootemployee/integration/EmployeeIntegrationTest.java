package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository1 employeeRepository1;

    @AfterEach
    void tearDown(){
        employeeRepository1.deleteAll();
    }

    @Test
    public void should_return_all_employees_when_get_all_given_employees() throws Exception {
        //given
        Employee employee = new Employee("Ken", 18, "male", 100000);
        employeeRepository1.save(employee);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("Ken"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(10000));
    }

    @Test
    public void should_return_employee_when_create_employee_given_employee() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "    \"name\": \"Ken\",\n" +
                "    \"age\": 21,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 10000\n" +
                "    \"companyId\": 1\n" +
                "}";

        //when

        //then
        mockMvc.perform(post("/employees")
                .contentType(APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("Ken"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(10000));

        List<Employee> employees = employeeRepository1.findAll();
        assertEquals(1, employees.size());
        assertEquals("Ken", employees.get(0).getName());
        assertEquals("18", employees.get(0).getAge());
    }

    @Test
    public void should_return_updated_employee_when_update_employee_given_employee() throws Exception {
        //given
        Employee employee = employeeRepository1.save(new Employee("Ken", 18, "male", 100000));
        String employeeToString = " {\n" +
                "            \"name\": \"Ken\",\n" +
                "            \"age\": 18,\n" +
                "            \"gender\": \"male\",\n" +
                "            \"salary\": 100000\n" +
                "    }";
        //when

        //then
        mockMvc.perform(put("/employees/" + employee.getId()).contentType(MediaType.APPLICATION_JSON).content(employeeToString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value("Ken"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.salary").value(100000))
                .andExpect(jsonPath("$.gender").value("male"));
    }

    @Test
    public void should_delete_employee_when_delete_given_employee_id() throws Exception {
        //given
        Employee employee = employeeRepository1.save(new Employee("Ken", 18, "Male", 100000));

        //when

        //then
        mockMvc.perform(delete("/employees/" + employee.getId()))
                .andExpect(status().isOk());
        assertFalse(employeeRepository1.findById(employee.getId()).isPresent());
    }


    @Test
    void should_return_employee_when_get_employee_given_employee_Id() throws Exception {
        //given
        Employee employee = employeeRepository1.save(new Employee("1", "Ken", 18, "male", 10000,  "1"));

        //when
        //then
        mockMvc.perform(get("/employees/"+employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value("Ken"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(10000))
                .andExpect(jsonPath("$.companyId").value("1"));;
    }

    @Test
    void should_return_all_male_employees_when_get_employee_given_gender_male() throws Exception {
        //given
        Employee employee = employeeRepository1.save(new Employee("Ken", 22, "male",  10000));
        employeeRepository1.save(new Employee("Kate", 18, "female",  10000));

        //when
        //then
        mockMvc.perform(get("/employees" +"?gender=male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee.getId()))
                .andExpect(jsonPath("$[0].name").value("Ken"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(10000));
    }

    @Test
    public void should_return_limited_employees_by_page_when_get_employee_by_page_given_page_size() throws Exception {
        //given
        Employee employee1 = employeeRepository1.save(new Employee("Ken", 18, "male", 100000));
        Employee employee2 = employeeRepository1.save(new Employee("Kenny", 20, "male", 100000));
        employeeRepository1.save(employee1);
        employeeRepository1.save(employee2);
        //when

        //then
        mockMvc.perform(get("/employees").param("page", "1").param("pageSize", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee1.getId()))
                .andExpect(jsonPath("$[0].name").value("Ken"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(100000))
                .andExpect(jsonPath("$[1].id").value(employee2.getId()))
                .andExpect(jsonPath("$[1].name").value("Kenny"))
                .andExpect(jsonPath("$[1].age").value(20))
                .andExpect(jsonPath("$[1].gender").value("male"))
                .andExpect(jsonPath("$[1].salary").value(100000));

    }


}