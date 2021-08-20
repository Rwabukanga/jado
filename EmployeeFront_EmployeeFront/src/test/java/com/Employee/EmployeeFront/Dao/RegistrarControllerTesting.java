package com.Employee.EmployeeFront.Dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.Employee.EmployeeFront.Controller.RegistrarController;
import com.Employee.EmployeeFront.Domain.Category;
import com.Employee.EmployeeFront.Domain.Gender;
import com.Employee.EmployeeFront.Domain.Registrant;
import com.Employee.EmployeeFront.Domain.SystemUser;
import com.Employee.EmployeeFront.Service.ISystemUserService;
import com.Employee.EmployeeFront.Service.RegistrarService;
import com.Employee.EmployeeFront.Utility.Encryption;
import com.Employee.EmployeeFront.Utility.Msg;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxResultset.ContentType_BYTES;

@RunWith(SpringRunner.class)
@WebMvcTest(value=RegistrarController.class, secure = false)
public class RegistrarControllerTesting {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private RegistrarService regservice;
	
	@MockBean
	private ISystemUserService systemservice;
	
	
	  @Test
	  //@Ignore
	  public void createRegistrarTest() throws Exception {
	  
	  Registrant rr = new Registrant(); 
	  rr.setId(1); 
	  rr.setFirstname("Jean");
	  rr.setLastname("Jado"); 
	  rr.setIdnumber("11994802535165");
	  rr.setPhone("0784331518"); 
	  rr.setUsername("jj"); 
	  rr.setDob(new Date());
	  rr.setPassword("jean"); 
	  rr.setEmail("seth@gmail.com");
	  rr.setGender(Gender.Male); 
	  rr.setCategory(Category.Admin);
	  
	  
	  SystemUser rt = new SystemUser(); 
	  rt.setId(1); 
	  rt.setFirstname("Jean");
	  rt.setLastname("Jado"); 
	  rt.setPhone("0784331518"); 
	  rt.setUsername("jj");
	  rt.setDob(new Date()); 
	  rt.setPassword("jean");
	  
	  String message="";
	  
	  String inputInJson = this.mapToJson(rr);
	  
	  String URI = "/registrar/save";
	  Mockito.when(regservice.createregistrar(Mockito.any(Registrant.class))).
	  thenReturn(rr);
	  Mockito.when(systemservice.create(Mockito.any(SystemUser.class))).thenReturn(
	  message); 
	  RequestBuilder requestbuilder = MockMvcRequestBuilders .post(URI)
	  .accept(MediaType.APPLICATION_JSON).content(inputInJson)
	  .contentType(MediaType.APPLICATION_JSON);
	  
	  MvcResult result = mockmvc.perform(requestbuilder).andReturn();
	  MockHttpServletResponse response = result.getResponse(); 
	  String outputInJson = response.getContentAsString();
	  
		/* assertThat(outputInJson).isEqualTo(inputInJson); */
	  assertEquals(HttpStatus.OK.value(), response.getStatus());
	  
	  }
	 

	
	  private String mapToJson(Object object) throws JsonProcessingException {
	  ObjectMapper objectmapper = new ObjectMapper(); return
	  objectmapper.writeValueAsString(object); }
	 
	
	@Test
	//@Ignore
	public void testGetAllRegistrant() throws Exception {
		
		Registrant rr = new Registrant();
		rr.setFirstname("Jean");
		rr.setLastname("Jado");
		rr.setIdnumber("11994802535165");
		rr.setPhone("0784331518");
		rr.setUsername("jj");
		rr.setPassword("jean"); 
		rr.setEmail("seth@gmail.com");
		rr.setGender(Gender.Male); 
		rr.setCategory(Category.Admin);
		rr.setDob(new Date());
		
		Registrant rg = new Registrant();
		rg.setFirstname("Abdul");
		rg.setLastname("Suleiman");
		rg.setIdnumber("11994802535165");
		rg.setPhone("0784331518");
		rg.setUsername("as");
		rr.setPassword("jean"); 
		rr.setEmail("seth@gmail.com");
		rr.setGender(Gender.Male); 
		rr.setCategory(Category.Admin);
		rr.setDob(new Date());
		
		List<Registrant> list = new ArrayList<>();
		list.add(rr);
		list.add(rg);
		
		Mockito.when(regservice.findAll()).thenReturn(list);
		String URI = "/registrar/all";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
          MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(list);
		String outputInJson = result.getResponse().getContentAsString();
		/* assertThat(outputInJson).isEqualTo(expectedJson); */
		
	}
	
	@Test
	@Ignore
	public void testgetRegistrarById() throws Exception {
		
		Registrant rtt = new Registrant();
		Optional<Registrant> rr = regservice.findByid(rtt.getId());
		Registrant rt = rr.get();
		rt.setId(1);
		rt.setFirstname("Jean");
		rt.setLastname("Jado");
		rt.setIdnumber("11994802535165");
		rt.setPhone("0784331518");
		rt.setUsername("jj");
		rt.setPassword("jean"); 
		rt.setEmail("seth@gmail.com");
		rt.setGender(Gender.Male); 
		rt.setCategory(Category.Admin);
		rt.setDob(new Date());
		
		
		Mockito.when(regservice.findByid(rt.getId()));
		String URI = "/registrar/id/id";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(
          MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		String outputInJson = result.getResponse().getContentAsString();
	}
}
