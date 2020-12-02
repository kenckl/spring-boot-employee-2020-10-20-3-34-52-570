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
}
