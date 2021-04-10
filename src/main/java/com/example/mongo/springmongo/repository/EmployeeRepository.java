package com.example.mongo.springmongo.repository;


import com.example.mongo.springmongo.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

   // @Query("{ 'name' : ?0}")
   // @Query("{'name':{'$regex':'?0','$options':'i'}}")
   //@Query(value="{ 'firstname' : ?0 }", fields="{ 'lastname' : 0, 'age' : 0}")
   @Query(value="{ 'firstname' : ?0 }", fields="{ 'lastname' : 1, 'age' : 1}")
   List<Employee> findByTheEmployeesFirstname(String firstname);

   @Query("{ 'name' : ?0, 'department' : ?1}")
   List<Employee> findByTheEmployeesNameAndDepartment(String name,String department);

   @Query("{ 'name' : ?0 }")
   List<Employee> findByName(String regexp);


   // @Query("{ 'name' : {'$regex':'?0'} }")
   @Query("{'lastname':{'$regex':'?0','$options':'i'}}")
   List<Employee> findByTheEmployeesLastName(String regexp);

   @Query("{age : { $gt : ?0, $lt : ?1}}")
   List<Employee> findByEmployeesByAgeBetween(int ageGreaterThan, int ageLesserThan, Sort sort);

   List<Employee> findByFirstnameOrderByLastname(String regex);

   @Query("{ 'lastname' : {$ne : null}, 'department': ?0 }")
   List<Employee> getUnsavedAge(String department);



}



