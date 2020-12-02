package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.controller.EmployeesController;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> findAllEmployees() {
        return employees;
    }
    
    public Employee addEmployee(Employee employee){
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployeeById(Integer id, Employee newEmployee){
        employees.stream()
             .filter(employee -> employee.getId() == id)
             .findFirst()
             .ifPresent(employee -> {
                 employees.remove(employee);
                 employees.add(newEmployee);
             });
        return newEmployee;
    }
}
