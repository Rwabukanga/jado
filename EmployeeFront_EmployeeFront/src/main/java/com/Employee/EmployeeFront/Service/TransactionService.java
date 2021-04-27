package com.Employee.EmployeeFront.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Domain.transactiontable;

@Service
public interface TransactionService {

	public void createtransaction(transactiontable trans);
	public List<transactiontable> findall(Class c);
	
}
