package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.example.demo.bean.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.mongodb.MongoClient;

@SpringBootApplication
public class Demoapp1Application {
	
	public static ConfigurableApplicationContext context;
	@Autowired
	public static MongoTemplate mongoTemplate;
	//@Autowired
	public static EmployeeRepository empRepo;

	public static void main(String[] args) {
		context = SpringApplication.run(Demoapp1Application.class, args);
		System.out.println("*********************************************");
		
		Employee emp = new Employee();
		emp.setEmail("mwendapeter72@gmail.com");
		emp.setFirstName("Peter");
		emp.setMiddleName("Mwenda");
		emp.setLastName("Njeru");
		context.getBean(EmployeeRepository.class).save(emp);
	}
	
	
	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		MongoClient mongoClient = new MongoClient("localhost");//27017
		return new SimpleMongoDbFactory(mongoClient, "EmployeeApp");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

}
