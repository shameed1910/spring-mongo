package com.example.mongo.springmongo.service;

import com.example.mongo.springmongo.model.Employee;
import com.example.mongo.springmongo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
public class EmployeeService {

        @Autowired
        private EmployeeRepository employeeRepository;
        @Autowired
        private MongoTemplate mongoTemplate;

        public List<Employee> findByThePersonsLastName( final String lastname){
        return employeeRepository.findByTheEmployeesLastName(lastname);
    }

        public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

        public List<Employee> findByThePersonsFirstname(final String firstname){
        return  employeeRepository.findByTheEmployeesFirstname(firstname);

    }

        public List<Employee> findByAgeBetween(final int minAge, final int maxAge, final Sort sort){
        return employeeRepository.findByEmployeesByAgeBetween(minAge,maxAge, sort);

    }

        public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

        public void delete(String id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> findByName(String name){
        /*Query query=new Query();
        query.addCriteria(Criteria.where("name").is(name));
        List<Employee> persons=mongoTemplate.find(query,Employee.class);
        return persons;*/
        return employeeRepository.findByName(name);
    }


    public List<Employee> saveAll(List<Employee> employeeList) {
            return employeeRepository.saveAll(employeeList);
    }

    public List<Employee> findByTheEmployeesNameAndDepartment(String name,String department){
        return employeeRepository.findByTheEmployeesNameAndDepartment(name,department);

    }

    public void deleteAll(){
            employeeRepository.deleteAll();
    }
}
