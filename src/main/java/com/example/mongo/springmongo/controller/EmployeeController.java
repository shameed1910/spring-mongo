package com.example.mongo.springmongo.controller;

import com.example.mongo.springmongo.model.AgeCount;
import com.example.mongo.springmongo.model.Employee;
import com.example.mongo.springmongo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    MongoTemplate mongoTemplate;

   /* @PostMapping
    public Employee save(@RequestBody Employee person){
        *//*Employee person = Employee.builder().id(reportSpec.getId())
                .name(reportSpec.getName()).build();
        Employee.PersonBuilder reportSpecDocumentBuilder= person.toBuilder();
        person =reportSpecDocumentBuilder.id(reportSpec.getId()).build();*//*
        return employeeService.save(person);
    }*/
    @PostMapping
    public List<Employee> saveAll(@RequestBody List<Employee> employeeList){
        return employeeService.saveAll(employeeList);
    }

    @GetMapping("/lastName/{lastname}")
    public List<Employee> findByThePersonsLastName(@PathVariable(value="lastname") final String lastname){
        return employeeService.findByThePersonsLastName(lastname);
    }

    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/{firstname}")
    public List<Employee> findByThePersonsFirstname(@PathVariable(value="firstname") final String firstname){
        return  employeeService.findByThePersonsFirstname(firstname);

    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        employeeService.delete(id);
    }
/*
    @DeleteMapping
    public void deleteAll(){
        employeeService.deleteAll();
    }*/



    @GetMapping("/{minAge}/{maxAge}")
    public List<Employee> findByAgeBetween(@PathVariable(value="minAge") final int minAge,
                                           @PathVariable(value="maxAge") final int maxAge){
        return employeeService.findByAgeBetween(minAge,maxAge,Sort.by("age").descending());

    }

   /* @GetMapping("/{name}/{department}")
    public List<Employee> findByTheEmployeesNameAndDepartment(@PathVariable("name") String name,
                                                              @PathVariable("department") String department){
        return employeeService.findByTheEmployeesNameAndDepartment(name,department);
    }*/

@GetMapping("/ageMatch/{age}")
    public List<AgeCount> ageMatch(@PathVariable(value = "age") final int age){
    GroupOperation groupByDept = Aggregation.group("age").count().as("count");

    MatchOperation ageMatch =Aggregation.match(new Criteria("count").gt(age));

    SortOperation sortByAgeDesc = Aggregation.sort(Sort.by(Sort.Direction.DESC, "count"));

    Aggregation aggregation
            = Aggregation.newAggregation(groupByDept,ageMatch,sortByAgeDesc);
    AggregationResults output
            = mongoTemplate.aggregate(aggregation, "employee", AgeCount.class);
    return output.getMappedResults();
    }

}
