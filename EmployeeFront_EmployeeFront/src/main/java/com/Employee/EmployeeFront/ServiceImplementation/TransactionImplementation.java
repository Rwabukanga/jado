/*package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.transactiontable;
import com.Employee.EmployeeFront.Domain.transactiontable;
import com.Employee.EmployeeFront.Service.TransactionService;

@Service
public class TransactionImplementation implements TransactionService {

	@Autowired
	private transactiontable transrepo;

	@Override
	public void createtransaction(transactiontable trans) {
		
		transrepo.save(trans);
		
	}

	@Override
	public List<transactiontable> findall(Class c) {
		// TODO Auto-generated method stub
		return transrepo.findAll();
	}

	@Override
	public void createtransaction(com.Employee.EmployeeFront.Domain.transactiontable trans) {
		// TODO Auto-generated method stub
		transrepo.save(arg0)
	}
	
	
	
	
}
*/