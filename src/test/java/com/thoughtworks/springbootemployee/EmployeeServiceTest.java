package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository1 repository1;

    @InjectMocks
    EmployeeService employeeService;

    //add more employee object for testing and reduce each page size---db

    @Test
    void should_return_all_when_get_all_given_some_employees_in_database(){
        //given
        final List<Employee> expected = Arrays.asList(new Employee("1", "Ken", 21, "male", 10000, "1"));
        when(repository1.findAll()).thenReturn(expected);

        //when
        final List<Employee> actual = employeeService.getAllEmployees();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_employee_when_get_by_id_given_in_database() throws EmployeeNotFoundException {
        //given
        Employee expected = new Employee("1", "Ken", 21, "male", 10000, "1");
        when(repository1.findById("1")).thenReturn(Optional.of(expected));

        //when
        Employee actual = employeeService.findEmployeeById("1");

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_employee_when_save_given_in_database(){
        //given
        Employee expected = new Employee("1", "Ken", 21, "male", 10000, "1");
        when(repository1.save(expected)).thenReturn(expected);

        //when
        Employee actual = employeeService.createEmployees(expected);

        //then
        assertEquals(expected, actual);

    }

    @Test
    void should_return_employee_when_get_by_gender_given_in_database(){
        //given
        Employee employee1 = new Employee("1", "Ken", 21, "male", 10000, "1");
        Employee employee2 = new Employee("1", "Kenny", 21, "male", 10000, "1");
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);
        when(repository1.findByGender("male")).thenReturn(expected);

        //when
        List<Employee> actual = employeeService.getEmployeeByGender("male");

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_update_employee_when_update_by_id_given_in_database() throws EmployeeNotFoundException {
        //given
        Employee expected = new Employee("1", "Ken", 21, "male", 10000, "1");
        when(repository1.existsById("1")).thenReturn(true);
        when(repository1.save(expected)).thenReturn(expected);

        //when
        Employee actual = employeeService.updateEmployeeById("1", expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_delete_employee_when_delete_by_id_given_in_database(){
        //given
        Employee employee = new Employee("1", "Ken", 21, "male", 10000, "1");

        //when
        employeeService.deleteEmployeeById("1");

        //then
        verify(repository1, times(1)).deleteById("1");
    }

    @Test
    void should_get_limited_employee_when_get_employee_wth_page_size_given_in_database(){
        //given
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("1", "Ken1", 21, "male", 10000, "1");
        employees.add(employee1);
        Page<Employee> expected = new PageImpl<>(employees);
        when(repository1.findAll((Pageable)any())).thenReturn(expected);

        //when
        Page<Employee> actual = employeeService.getEmployeeByPage(2,2);

        //then
        assertEquals(expected, actual);
    }

}
