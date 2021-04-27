/*package com.Employee.EmployeeFront;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;


@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class AppSecurityConfig extends WebSecurityConfigurerAdapter 

{
	
	protected void configurer(HttpSecurity http) throws Exception{
		
		http
		
		.csrf().disable()
		.authorizeRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated();
	
		
	}

}
*/