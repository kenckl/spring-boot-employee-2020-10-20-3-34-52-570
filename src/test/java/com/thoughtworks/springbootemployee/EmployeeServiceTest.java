package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Test
    public void should_return_employee_lists_when_get_employees(){
        //given
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        List<Employee> expected = asList(new Employee(), new Employee());
        when(repository.findAllEmployees()).thenReturn(expected);
        EmployeeService service = new EmployeeService(repository);

        //when
        List<Employee> actual = service.getAllEmployees();

        //given
        assertEquals(2, actual.size());
    }
}
