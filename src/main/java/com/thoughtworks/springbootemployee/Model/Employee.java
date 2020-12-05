package com.thoughtworks.springbootemployee.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Employee {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private String companyId;

    public Employee(String id, String name, Integer age, String gender, Integer salary, String companyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Employee(String name, Integer age, String gender, Integer salary, String companyId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public Employee(String name, Integer age, String gender, Integer salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Employee() {

    }

    public void setId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
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

    public String getCompanyId() {
        return companyId;
    }

    public String getJSON(){
        return String.format("    {\n" +
                "        \"id\": \"%s\",\n" +
                "        \"name\": \"%s\",\n" +
                "        \"age\": %d,\n" +
                "        \"gender\": \"%s\",\n" +
                "        \"salary\": %d\n" +
                "    }",id,name,age,gender,salary);
    }
}

