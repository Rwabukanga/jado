package com.Solar.SolarEnergy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
public class WebConfig {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurerAdapter() {
			
			public void addCorsMapping(CorsRegistry registrar) {
				registrar.addMapping("/**").allowedOrigins("http://localhost:4002").allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
		
	}

}
