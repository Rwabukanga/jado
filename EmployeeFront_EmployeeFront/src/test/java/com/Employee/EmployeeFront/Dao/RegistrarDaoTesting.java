package com.Employee.EmployeeFront.Dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.Employee.EmployeeFront.Domain.Registrant;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegistrarDaoTesting {

	@Autowired
	private TestEntityManager entitymanager;
	
	@Autowired
	private RegistrarDao regdao;
	
	
	@Test
	public void testsaveRegistrar() {
		
		Registrant rr = getRegistrant();
		Registrant saveInDb = entitymanager.persist(rr);
		
		Registrant getFromDb = regdao.getOne(saveInDb.getId());
		
		assertThat(getFromDb).isEqualTo(saveInDb);
	}
	
	private Registrant getRegistrant() {
		
		Registrant rr = new Registrant();
		
		rr.setFirstname("Jean");
		rr.setLastname("Jado");
		rr.setIdnumber("11994802535165");
		rr.setPhone("0784331518");
		rr.setUsername("jj");
		
		return rr;
	}
	
	@Test
	public void testGetRegistrarById() {
		
		Registrant rr = new Registrant();
		rr.setFirstname("Jean");
		rr.setLastname("Jado");
		rr.setIdnumber("11994802535165");
		rr.setPhone("0784331518");
		rr.setUsername("jj");
		
		Registrant registrantSaveInDb = entitymanager.persist(rr);
		
		Registrant registrantFromInDb = regdao.getOne(registrantSaveInDb.getId());
		
		assertThat(registrantSaveInDb).isEqualTo(registrantFromInDb);
	}
	
	@Test
	public void testGetAllRegistrant() {
		
		Registrant rr = new Registrant();
		rr.setFirstname("Jean");
		rr.setLastname("Jado");
		rr.setIdnumber("11994802535165");
		rr.setPhone("0784331518");
		rr.setUsername("jj");
		
		Registrant rg = new Registrant();
		rg.setFirstname("Abdul");
		rg.setLastname("Suleiman");
		rg.setIdnumber("11994802535165");
		rg.setPhone("0784331518");
		rg.setUsername("as");
		
		entitymanager.persist(rr);
		entitymanager.persist(rg);
		
		Iterable<Registrant> allRegistrantFromDb = regdao.findAll();
		List<Registrant> list = new ArrayList<>();
		
		
		for (Registrant registrant : allRegistrantFromDb) {
			list .add(registrant);
		}
		assertThat(list.size()).isEqualTo(2);
	}
	
	@Test
	public void testDeleteRegistrantById() {
		Registrant rr = new Registrant();
		rr.setFirstname("Jean");
		rr.setLastname("Jado");
		rr.setIdnumber("11994802535165");
		rr.setPhone("0784331518");
		rr.setUsername("jj");
		
		Registrant rg = new Registrant();
		rg.setFirstname("Abdul");
		rg.setLastname("Suleiman");
		rg.setIdnumber("11994802535165");
		rg.setPhone("0784331518");
		rg.setUsername("as");
		
		Registrant persist = entitymanager.persist(rr);
		entitymanager.persist(rg);
		
		entitymanager.remove(persist);
		
		Iterable<Registrant> allRegistrantFromDb = regdao.findAll();
		List<Registrant> list = new ArrayList<>();
		
		
		for (Registrant registrant : allRegistrantFromDb) {
			list.add(registrant);
		}
		assertThat(list.size()).isEqualTo(1);
	}
}
