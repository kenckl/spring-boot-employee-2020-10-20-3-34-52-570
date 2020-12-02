package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private EmployeeService employeeService = new EmployeeService(new EmployeeRepository());
    private final List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getEmployeesList(){
        return employees;
    }
}
