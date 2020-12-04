package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository1 employeeRepository1;

    public List<Employee> getAllEmployees(){
        return employeeRepository1.findAll();
    }

    public Employee createEmployees(Employee employee){
        return employeeRepository1.save(employee);
    }

    // similar to company service
    public Employee updateEmployeeById(String id, Employee employee) throws EmployeeNotFoundException {
        if (employeeRepository1.existsById(id)){
            employee.setId(id);
            return employeeRepository1.save(employee);
        }
        throw new EmployeeNotFoundException();
    }

    public void deleteEmployeeById(String id){
        employeeRepository1.deleteById(id);
    }

    public Employee findEmployeeById(String id) throws EmployeeNotFoundException {
        return employeeRepository1.findById(id).orElseThrow(() -> new EmployeeNotFoundException());
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository1.findByGender(gender);
    }

    public List<Employee> getEmployeeByPage(Integer page, Integer pageSize) {
        return employeeRepository1.findAll().stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeeById(String id){
        return employeeRepository1.findByCompanyId(id);
    }
}
