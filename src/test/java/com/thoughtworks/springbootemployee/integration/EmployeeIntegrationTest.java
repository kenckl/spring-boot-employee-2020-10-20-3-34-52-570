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
    public void should_return_updated_employee_when_update_employee_given_an_employee() throws Exception {
        //given
        employeeRepository1.deleteAll();
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
    void should_return_employee_when_get_employee_given_employee_Id() throws Exception {
        //given
        Employee employee = employeeRepository1.save(new Employee("1", "Ken", 18, "male", 10000,  "1"));
        employeeRepository1.save(new Employee("2", "Kenny", 18, "male", 10000,  "1"));

        //when
        //then
        mockMvc.perform(get("/employees/"+employee.getId()))
                .andExpect(jsonPath("$.employeeId").isString())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.age").value(employee.getAge()))
                .andExpect(jsonPath("$.gender").value(employee.getGender()))
                .andExpect(jsonPath("$.salary").value(employee.getSalary()))
                .andExpect(jsonPath("$.companyId").value("1"));;

        List<Employee> employeeList = employeeRepository1.findAll();
        Assertions.assertEquals(2, employeeList.size());
        Assertions.assertEquals("Ken", employeeList.get(0).getName());
    }

    @Test
    void should_return_all_male_employees_when_get_employee_given_gender_male() throws Exception {
        //given
        Employee employee = employeeRepository1.save(new Employee("3", "Ken", 22, "male",  10000,"2"));
        employeeRepository1.save(new Employee("4", "Kate", 18, "female",  10000,"3"));

        //when
        //then
        mockMvc.perform(get("/employees" +"?gender="+employee.getGender()))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andExpect(jsonPath("$[0].age").value(employee.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee.getSalary()))
                .andExpect(jsonPath("$.companyId").value("2"));;

        List<Employee> employeeList = employeeRepository1.findAll();
        Assertions.assertEquals(2, employeeList.size());
        Assertions.assertEquals("male", employeeList.get(0).getGender());
    }

}