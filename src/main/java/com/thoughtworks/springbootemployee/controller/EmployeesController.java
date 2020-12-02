package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private EmployeeService employeeService = new EmployeeService(new EmployeeRepository());
    private final List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getEmployeesList(){
        return employees;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee createEmployee){
        employees.add(createEmployee);
        return createEmployee;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer employeeId){
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId)).findFirst().orElse(null);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployeeById(@PathVariable Integer employeeId, @RequestBody Employee employeeUpdate) {
        employees.stream()
                .filter(employee -> employee.getId().equals(employeeId)).findFirst()
                .ifPresent(employee -> {
                    employees.remove(employee);
                    employees.add(employeeUpdate);
                });
        return employeeUpdate;
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeById(@PathVariable Integer employeeId){
        employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .ifPresent(employees::remove);
    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeeByGender(@RequestParam("gender") String gender){
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }
}
