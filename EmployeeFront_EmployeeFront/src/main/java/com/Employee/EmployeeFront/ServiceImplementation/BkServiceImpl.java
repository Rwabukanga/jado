package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Employee.EmployeeFront.Dao.BkRepository;
import com.Employee.EmployeeFront.Domain.BK;
import com.Employee.EmployeeFront.Service.BkService;
import com.Employee.EmployeeFront.Utility.Encryption;
import com.Employee.EmployeeFront.Utility.Messages;

@Service
@Transactional
public class BkServiceImpl implements BkService {
	
	private BkRepository bkrepo;

	@Override
	public String create(BK bb) {
		String message;
		try {
			bb.setPassword(Encryption.md5(bb.getPassword()));
			bkrepo.save(bb);
			message = Messages.save;
			
		}catch(Exception e) {
			message = Messages.error;
		}
		return message;
	}

	@Override
	public List<BK> findall(Class c) {
		// TODO Auto-generated method stub
		return bkrepo.findAll();
	}

	@Override
	public Optional<BK> findById(long id) {
		// TODO Auto-generated method stub
		return bkrepo.findById(id);
	}

	

	

}
