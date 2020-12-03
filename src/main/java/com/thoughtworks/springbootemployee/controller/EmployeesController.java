package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;
    private final List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getEmployeesList(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee createEmployee){
        return employeeService.createEmployees(createEmployee);
    }

    // return employeeService.getEmployeeById();
    @GetMapping("/{employeeId}")
    public Optional<Employee> getEmployeeById(@PathVariable String employeeId){
        return employeeService.findEmployeeById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployeeById(@PathVariable String employeeId, @RequestBody Employee employeeUpdate) {
        return employeeService.updateEmployeeById(employeeId, employeeUpdate);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeById(@PathVariable String employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeeByGender(@RequestParam("gender") String gender){
        return employeeService.getEmployeesByGender(gender);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return employeeService.getEmployeeByPage(page, pageSize);
    }
}
