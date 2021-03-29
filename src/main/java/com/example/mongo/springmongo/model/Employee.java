package com.example.mongo.springmongo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Employee {

    private String id;
    private String name;
    private String firstname;
    private String lastname;
    private int age;
    private String department;

}
