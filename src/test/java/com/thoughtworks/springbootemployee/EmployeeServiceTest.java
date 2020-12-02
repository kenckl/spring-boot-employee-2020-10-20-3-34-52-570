package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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
        when(repository.add(employee)).thenReturn(employee);

        //when
        Employee actualEmployee = employeeService.createEmployees(employee);

        //then
        assertSame(1, actualEmployee.getId());
    }
}
