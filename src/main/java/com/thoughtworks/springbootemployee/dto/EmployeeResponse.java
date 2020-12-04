package com.thoughtworks.springbootemployee.dto;

public class EmployeeResponse {
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;

    public EmployeeResponse(String id, String name, Integer age, String gender, Integer salary){
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public EmployeeResponse(){}

    //add mapper and getter
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
