package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.controller.EmployeesController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteEmployeeById(Integer id) {
        employees.stream()
                .filter(employee -> employee.getId() == (id))
                .findFirst()
                .ifPresent(employees::remove);
    }

    public Employee findEmployeeById(Integer id){
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeeByPage(Integer page, Integer pageSize){
        return employees.stream()
                .skip((pageSize -1) * page)
                .limit(pageSize)
                .collect(Collectors.toList());

    }
}
