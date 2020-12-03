package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository1 extends MongoRepository<Employee,String>{
    Optional<Employee> findById(String id);
    List<Employee> findByGender(String gender);
    List<Employee> findByCompanyId(String companyId);
}
