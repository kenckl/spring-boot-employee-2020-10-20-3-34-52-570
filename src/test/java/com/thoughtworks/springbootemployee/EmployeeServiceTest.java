package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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
    EmployeeRepository repository;
    @Mock
    EmployeeRepository1 repository1;

    @InjectMocks
    EmployeeService employeeService;

//    @Test
//    public void should_return_employee_lists_when_get_employees(){
//        //given
//        List<Employee> expected = asList(new Employee(), new Employee());
//        when(repository1.findAll()).thenReturn(expected);
//
//        //when
//        List<Employee> actualEmployee = employeeService.getAllEmployees();
//
//        //given
//        assertEquals(2, actualEmployee.size());
//    }
//
//    @Test
//    public void should_create_employee_when_create_given_employee() {
//        //given
//        Employee employee = new Employee("1", "Ken", 18, "male", 100000);
//        EmployeeService employeeService = new EmployeeService(repository);
//        when(repository.addEmployee(employee)).thenReturn(employee);
//
//        //when
//        Employee actualEmployee = employeeService.createEmployees(employee);
//
//        //then
//        assertSame(1, actualEmployee.getId());
//    }
//
//    @Test
//    void should_update_employee_when_update_employee_by_id_given_employee_id() {
//        //given
//        Employee employee = new Employee("1", "Ken", 18, "male", 100000);
//        Employee newEmployee = new Employee("1", "Ken", 18, "male", 200000);
//        when(repository.updateEmployeeById(employee.getId(), employee)).thenReturn(newEmployee);
//        EmployeeService employeeService = new EmployeeService(repository);
//
//        //when
//        Employee actualEmployee = employeeService.updateEmployeeById(employee.getId(), employee);
//
//        //then
//        assertNotEquals(employee.getSalary(), actualEmployee.getSalary());
//    }
//
//    @Test
//    void should_delete_employee_when_delete_employee_by_id_when_delete_given_employee_id() {
//        //given
//        Employee employee = new Employee("1", "Ken", 18, "male", 100000);
//        EmployeeService employeeService = new EmployeeService(repository);
//
//        //when
//        employeeService.deleteEmployeeById(employee.getId());
//
//        //then
//        verify(repository, times(1)).deleteEmployeeById(employee.getId());
//    }
//
//    @Test
//    void should_return_employee_when_get_employee_by_id_given_employee_id(){
//        //given
//        Employee employee = new Employee("1", "Ken", 18, "male", 100000);
//        when(repository.findEmployeeById(employee.getId())).thenReturn(employee);
//        EmployeeService employeeService = new EmployeeService(repository);
//
//        //when
//        Employee actualEmployee = employeeService.findEmployeeById(employee.getId());
//
//        //then
//        assertEquals(employee.getId(), actualEmployee.getId());
//    }

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
    void should_return_employee_when_get_by_id_given_in_database(){
        //given
        Optional<Employee> expected = Optional.of(new Employee("1", "Ken", 21, "male", 10000, "1"));
        when(repository1.findById("1")).thenReturn(expected);

        //when
        Optional<Employee> actual = employeeService.findEmployeeById("1");

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
    void should_return_update_employee_when_update_by_id_given_in_database(){
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
        Employee employee2 = new Employee("2", "Ken2", 21, "male", 10000, "1");
        Employee employee3 = new Employee("3", "Ken3", 21, "male", 10000, "1");
        Employee employee4 = new Employee("4", "Ken4", 21, "male", 10000, "1");
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        when(repository1.findAll()).thenReturn(employees);

        //when
        List<Employee> actual = employeeService.getEmployeeByPage(0,3);

        //then
        assertEquals(3, actual.size());
        assertEquals("1", actual.get(0).getId());
        assertEquals("2", actual.get(1).getId());
        assertEquals("3", actual.get(2).getId());

    }

}
