package com.Employee.EmployeeFront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.Employee.EmployeeFront.Domain.SystemUser;


@SpringBootApplication
public class EmployeeFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeFrontApplication.class, args);
	}
	
	
	
	/*protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmployeeFrontApplication.class); 
	}
	*/
	
	/*protected SpringApplicationBuilder configur(SpringApplicationBuilder application) {
		return application.sources(SystemUser.class);
	}
*/
}
