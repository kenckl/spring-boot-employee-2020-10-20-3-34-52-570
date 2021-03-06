package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeesController(EmployeeService employeeService, EmployeeMapper employeeMapper){
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }
    @GetMapping
    public List<EmployeeResponse> getEmployeesList(){
        return employeeService.getAllEmployees().stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest createEmployee){
        Employee employee = employeeService.createEmployees(employeeMapper.toEntity(createEmployee));
        return employeeMapper.toResponse(employee);
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponse getEmployeeById(@PathVariable String employeeId) throws EmployeeNotFoundException {
        return employeeMapper.toResponse(employeeService.findEmployeeById(employeeId));
    }

    @PutMapping("/{employeeId}")
    public EmployeeResponse updateEmployeeById(@PathVariable String employeeId, @RequestBody EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        Employee employee = employeeService.updateEmployeeById(employeeId, employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeById(@PathVariable String employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }

    @GetMapping(params = "gender")
    public List<EmployeeResponse> getEmployeeByGender(@RequestParam("gender") String gender){
        return employeeService.getEmployeeByGender(gender).stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<EmployeeResponse> getEmployeeByPage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return employeeService.getEmployeeByPage(page, pageSize).stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }
}
