package com.soenergy.SOLAREENERGY;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@Configuration
@SpringBootApplication
@EnableJpaAuditing
public class SolareenergyApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SolareenergyApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SolareenergyApplication.class); 
	}
	
	
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory h){
		return h.getSessionFactory();
		
	}

}
