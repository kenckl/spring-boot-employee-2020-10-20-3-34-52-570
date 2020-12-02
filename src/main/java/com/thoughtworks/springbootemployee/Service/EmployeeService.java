package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAllEmployees();
    }

    public Employee createEmployees(Employee employee){
        return employeeRepository.addEmployee(employee);
    }

    public Employee updateEmployeeById(Integer id, Employee employee){
        return employeeRepository.updateEmployeeById(id, employee);
    }

    public void deleteEmployeeById(Integer id){
        employeeRepository.deleteEmployeeById(id);
    }
}
