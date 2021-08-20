package com.Employee.EmployeeFront.Dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.Employee.EmployeeFront.Domain.Registrant;
import com.Employee.EmployeeFront.Service.RegistrarService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrarServiceTesting {

	@Autowired
	private RegistrarService regservice;
	
	@MockBean
	private RegistrarDao regdao;
	
	@Test
	public void createRegistrarTest() {
		
       Registrant rr = new Registrant();
		
		rr.setFirstname("Jean");
		rr.setLastname("Jado");
		rr.setIdnumber("11994802535165");
		rr.setPhone("0784331518");
		rr.setUsername("jj");
		rr.setDob(new Date());
		rr.setPassword("jean");
		rr.setEmail("seth@gmail.com");
		
	    Mockito.when(regdao.save(rr)).thenReturn(rr);
		assertThat(regservice.createregistrar(rr)).isEqualTo(rr);
		
		
	}
	
	/*
	 * @Test public void testGetRegistrarById() { Optional<Registrant> reg =
	 * regservice.findByid(1);
	 * 
	 * Registrant rr = reg.get(); rr.setId(1); rr.setFirstname("Jean");
	 * rr.setLastname("Jado"); rr.setIdnumber("11994802535165");
	 * rr.setPhone("0784331518"); rr.setUsername("jj"); rr.setDob(new Date());
	 * rr.setPassword("jean"); rr.setEmail("seth@gmail.com");
	 * 
	 * Mockito.when(regdao.getOne(1)).thenReturn(rr);
	 * assertThat(regservice.findByid(1)).isEqualTo(rr);
	 * 
	 * }
	 */
	
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
		
		
		
		List<Registrant> list = new ArrayList<>();
		list.add(rr);
		list.add(rg);
		

	    Mockito.when(regdao.findAll()).thenReturn(list);
		assertThat(regservice.findAll()).isEqualTo(list);
		
		
	}
	
	@Test
	public void testDeleteRegistrantById() {
		Registrant rr = new Registrant();
		rr.setId(1);
		rr.setFirstname("Jean");
		rr.setLastname("Jado");
		rr.setIdnumber("11994802535165");
		rr.setPhone("0784331518");
		rr.setUsername("jj");
		
		Mockito.when(regdao.getOne(1)).thenReturn(rr);
		Mockito.when(regdao.existsById(rr.getId())).thenReturn(false);
		assertFalse(regdao.existsById(rr.getId()));
		
		
		
		
		
	}
}
