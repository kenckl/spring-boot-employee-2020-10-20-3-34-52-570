package com.thoughtworks.springbootemployee.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Company {

    @MongoId(FieldType.OBJECT_ID)
    private String companyId;
    private String companyName;

    public Company(String companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

}
