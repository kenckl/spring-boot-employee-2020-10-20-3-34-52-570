package com.thoughtworks.springbootemployee.dto;

public class EmployeeRequest {
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;

    public EmployeeRequest(String name, Integer age, String gender, Integer salary){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public EmployeeRequest(){
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setName(){this.name = name;}

    public void setAge(){this.age = age;}

    public void setGender(){this.gender = gender;}

    public void setSalary(){this.name = name;}
}
