package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRepository1 employeeRepository1;


//    public EmployeeService(EmployeeRepository1 employeeRepository1){
//        this.employeeRepository1 = employeeRepository1;
//    }

    public List<Employee> getAllEmployees(){
        final Iterable<Employee> allId = employeeRepository1.findAllById(new ArrayList<>());
        return employeeRepository1.findAll();
    }

    public Employee createEmployees(Employee employee){
        return employeeRepository1.save(employee);
    }

    public Employee updateEmployeeById(String id, Employee employee){
        return employeeRepository.updateEmployeeById(id, employee);
    }

    public void deleteEmployeeById(String id){
        employeeRepository.deleteEmployeeById(id);
    }

    public Optional<Employee> findEmployeeById(String id){
        return employeeRepository1.findById(id);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.getEmployeeByGender(gender);
    }

    public List<Employee> getEmployeeByPage(Integer page, Integer pageSize) {
        return employeeRepository.getEmployeeByPage(page,pageSize);
    }
}
