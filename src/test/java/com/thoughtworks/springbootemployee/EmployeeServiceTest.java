package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
    EmployeeService service = new EmployeeService(repository);

    @Test
    public void should_return_employee_lists_when_get_employees(){
        //given
        List<Employee> expected = asList(new Employee(), new Employee());
        when(repository.findAllEmployees()).thenReturn(expected);

        //when
        List<Employee> actualEmployee = service.getAllEmployees();

        //given
        assertEquals(2, actualEmployee.size());
    }

    @Test
    public void should_create_employee_when_create_given_employee() {
        //given
        Employee employee = new Employee(1, "Ken", 18, "male", 100000);
        EmployeeService employeeService = new EmployeeService(repository);
        when(repository.addEmployee(employee)).thenReturn(employee);

        //when
        Employee actualEmployee = employeeService.createEmployees(employee);

        //then
        assertSame(1, actualEmployee.getId());
    }

    @Test
    void should_update_employee_when_update_employee_by_id_given_employee_id() {
        //given
        Employee employee = new Employee(1, "Ken", 18, "male", 100000);
        Employee newEmployee = new Employee(1, "Ken", 18, "male", 200000);
        when(repository.updateEmployeeById(employee.getId(), employee)).thenReturn(newEmployee);
        EmployeeService employeeService = new EmployeeService(repository);

        //when
        Employee actualEmployee = employeeService.updateEmployeeById(employee.getId(), employee);

        //then
        assertNotEquals(employee.getSalary(), actualEmployee.getSalary());
    }

    @Test
    void should_delete_employee_when_delete_employee_by_id_when_delete_given_employee_id() {
        //given
        Employee employee = new Employee(1, "Ken", 18, "male", 100000);
        EmployeeService employeeService = new EmployeeService(repository);

        //when
        employeeService.deleteEmployeeById(employee.getId());

        //then
        verify(repository, times(1)).deleteEmployeeById(employee.getId());
    }

    @Test
    void should_return_employee_when_get_employee_by_id_given_employee_id(){
        //given
        Employee employee = new Employee(1, "Ken", 18, "male", 100000);
        when(repository.findEmployeeById(employee.getId())).thenReturn(employee);
        EmployeeService employeeService = new EmployeeService(repository);

        //when
        Employee actualEmployee = employeeService.findEmployeeById(employee.getId());

        //then
        assertEquals(employee.getId(), actualEmployee.getId());
    }

    @Test
    void should_return_page_and_pageSize_when_getByPage_given_employee_request() {
        //given
        List<Employee> employeeList = asList(new Employee(), new Employee(), new Employee(), new Employee(), new Employee());
        when(repository.getEmployeeByPage(1, 5)).thenReturn(employeeList);
        EmployeeService employeeService = new EmployeeService(repository);

        //when
        List<Employee> actualEmployee = employeeService.getEmployeeByPage(1, 5);

        //then
        assertEquals(5, actualEmployee.size());
    }
}
