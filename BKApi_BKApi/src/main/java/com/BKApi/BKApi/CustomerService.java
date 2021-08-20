package com.BKApi.BKApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
	
	private static Map<Integer, Customer> customers = new HashMap<>();
	private static Integer index = 2;
	
	static {
		Customer customer1= new Customer (1,"Jean","2021-09-08","0788997756","Kigali");
		Customer customer2= new Customer (2,"Jado","2021-09-08","0788997756","Kigali");
		Customer customer3= new Customer (3,"Innocent","2021-09-08","0788997756","Kigali");
		customers.put(1, customer1);
		customers.put(2, customer2);
		customers.put(3, customer3);
	}
	
	public static List<Customer> getallCustomer(){
		return new ArrayList<>(customers.values());
	}

	public static Customer getEmployeeDetails(int id){
		return customers.get(id);
	}
	
	public static Customer addCustomer(Customer cust){
		
		index +=1;
		cust.setId(index);
		customers.put(index, cust);
		return cust;
	}
	
public static Customer updateCustomer(int id,Customer cust){
		
		
		cust.setId(index);
		customers.put(index, cust);
		return cust;
	}

public static Customer deleteCustomer(Integer id){
	
	return customers.remove(id);
}
	
}
