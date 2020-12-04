package com.thoughtworks.springbootemployee.exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(){
        super("Employee Not Found");
    }
}
