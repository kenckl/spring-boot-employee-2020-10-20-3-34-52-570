package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository1 extends MongoRepository<Employee,String>{
    List<Employee> findAll();
    Optional<Employee> findById(String id);
    Employee save (Employee employee);
    List<Employee> findByGender(String gender);


}
