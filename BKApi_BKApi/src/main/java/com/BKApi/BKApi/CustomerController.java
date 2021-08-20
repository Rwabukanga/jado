package com.BKApi.BKApi;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@GetMapping("/customerss")
	public List<Customer> getAllCustomer(){
		return CustomerService.getallCustomer();
	}
	
	@GetMapping("/custom/{id}")
	public Customer getCustomerDetails(@PathVariable int id){
		return CustomerService.getEmployeeDetails(id);
	}
	
	@PostMapping("/addcustomer")
	public Customer addCustomer(@RequestBody Customer customer){
		return CustomerService.addCustomer(customer);
	}
	
	@PutMapping("/customerr/{id}")
	public Customer updateCustomer(@PathVariable int id,@RequestBody Customer customer){
		return CustomerService.updateCustomer(id, customer);
	}
	
	@DeleteMapping("/deletecustomer/{id}")
	public Customer deleteCustomer(@PathVariable int id){
		return CustomerService.deleteCustomer(id);
	}
	
}
