package com.Solar.SolarEnergy.Controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Domain.District;
import com.Solar.SolarEnergy.Domain.Province;
import com.Solar.SolarEnergy.Domain.Registrar;
import com.Solar.SolarEnergy.Service.BranchService;
import com.Solar.SolarEnergy.Service.DistrictService;
import com.Solar.SolarEnergy.Service.Provinceservice;
import com.Solar.SolarEnergy.Service.RegistrarService;
import com.Solar.SolarEnergy.Utitlity.Messages;
import com.Solar.SolarEnergy.Utitlity.ResponseBean;


@RestController
@CrossOrigin
@RequestMapping(value="/branchs")
public class branchController {

	@Autowired
	private BranchService branchservice;
	
	@Autowired
	private RegistrarService registrarservice;
	
	@Autowired
	private Provinceservice provinceservice;
	
	@Autowired
	private DistrictService districtservice;

	@CrossOrigin
	@RequestMapping(value ="/save/{id}/{pid}", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createSeason(HttpServletRequest request, @RequestBody Branch branch,@PathVariable int id,@PathVariable int pid) {
		ResponseBean rb = new ResponseBean();
	
		try {
			         
			System.out.println(pid+"======================");
			          Province province = provinceservice.findByid(pid);
			          District district = districtservice.findByid(id);	
			          
			          /*  b.setBranchname(branch.getBranchname());
						b.setEmail(branch.getEmail());
						b.setPhonenumber(branch.getPhonenumber());
						b.setMadedate(branch.getMadedate());*/
						branch.setDistrict(district);
					    branch.setProvince(province);
			      
			          branchservice.createBranch(branch);
					  rb.setCode(Messages.SUCCESS_CODE);
					  rb.setDescription(Messages.save);
					  rb.setObject(branch);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create Branch");
			rb.setObject(branch);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value= "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAllBranch() {
		ResponseBean rb = new ResponseBean();
		try {

			
					List<Branch> list = branchservice.findAll();
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("The branches are successfuly retrieved");
					rb.setObject(list);


		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured while retrieving branches");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable int id, @PathVariable String uuid) {

		ResponseBean rb = new ResponseBean();
		try {

			
				
					Branch branch = branchservice.findByUuid(uuid);
					if (branch == null) {
						rb.setCode(Messages.NULLS_FOUND);
						rb.setDescription("The branch was not found");
					} else {
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The branch is successfuly found");
						rb.setObject(branch);
					}

		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured please try again!!");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	
}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable int id, @PathVariable String uuid) {
        
		ResponseBean rb = new ResponseBean();
		try {

					Branch branch = branchservice.findByUuid(uuid);

					if (branch == null) {
						rb.setCode(Messages.NOT_FOUND);
						rb.setDescription("The branch you want  Delete does not exit");
					} else {

						branchservice.deleteBranch(id);
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("Branch Deleted Successfuly");
						rb.setObject(null);
					}

				
		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Branch not well Deleted");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
}
	@CrossOrigin
	@RequestMapping(value ="/Adduser/registrar/{uuid}/branch/{buuid}", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createBranchuser(HttpServletRequest request, @PathVariable String uuid, @PathVariable String buuid) {
		ResponseBean rb = new ResponseBean();
		Branch branch = new Branch();
		
		try {
			            
			Registrar registrar = registrarservice.findByUuid(uuid);
			branchservice.findByUuid(buuid);
			branchservice.updateBranch(branch);
			
			   
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(branch);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create Season");
			rb.setObject(branch);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	 @RequestMapping(value = "/active", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> viewAllActiveSeason(HttpServletRequest request) {
			ResponseBean rb = new ResponseBean();
			try {
				String userToken = request.getHeader(Messages.token_name);
				if (userToken != null) {

					if (userToken.equalsIgnoreCase(Messages.token_name)) {
						List<Branch> list = branchservice.findAll();
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The branch are successfuly retrieved");
						rb.setObject(list);
					} else {
						rb.setCode(Messages.INCORRECT_TOKEN);
						rb.setDescription("INCCORECT TOKEN");
						rb.setObject(null);
					}
				} else {
					rb.setCode(Messages.TOKEN_NOT_FOUND);
					rb.setDescription(" TOKEN NOT FOUND");
					rb.setObject(null);
				}

			} catch (Exception e) {
				rb.setCode(Messages.ERROR_CODE);
				rb.setDescription("error occured while retrieving branch");
			}

			return new ResponseEntity<Object>(rb, HttpStatus.OK);
		}

	
}	
	