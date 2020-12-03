package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> findAllEmployees() {
        return employees;
    }
    
    public Employee addEmployee(Employee employee){
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployeeById(String id, Employee newEmployee){
        employees.stream()
             .filter(employee -> employee.getId() == id)
             .findFirst()
             .ifPresent(employee -> {
                 employees.remove(employee);
                 employees.add(newEmployee);
             });
        return newEmployee;
    }

    public void deleteEmployeeById(String id) {
        employees.stream()
                .filter(employee -> employee.getId() == (id))
                .findFirst()
                .ifPresent(employees::remove);
    }

    // use .equals() to replace ==
    public Employee findEmployeeById(String id){
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeeByPage(Integer page, Integer pageSize){
        return employees.stream()
                .skip((pageSize -1) * page)
                .limit(pageSize)
                .collect(Collectors.toList());

    }
}
