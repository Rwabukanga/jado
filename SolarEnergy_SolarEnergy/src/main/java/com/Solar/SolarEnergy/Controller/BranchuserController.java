package com.Solar.SolarEnergy.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Domain.Branchuser;
import com.Solar.SolarEnergy.Domain.Registrar;
import com.Solar.SolarEnergy.Service.BranchService;
import com.Solar.SolarEnergy.Service.BranchUserService;
import com.Solar.SolarEnergy.Service.RegistrarService;
/*import com.Solar.SolarEnergy.Utitlity.JavaEmail;*/
import com.Solar.SolarEnergy.Utitlity.Messages;
import com.Solar.SolarEnergy.Utitlity.ResponseBean;

@RestController
@CrossOrigin
@RequestMapping(value="/branchusers")
public class BranchuserController {
	
	/*@Autowired
	private JavaEmail javaemail;
	*/
	@Autowired
	private BranchUserService branchuserservice;
	
	@Autowired
	private RegistrarService registrarservice;
	
	
	@CrossOrigin
	@RequestMapping(value ="/save", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createSeason(HttpServletRequest request, @RequestBody Branchuser branch ) {
		ResponseBean rb = new ResponseBean();
	
		try {
			            branchuserservice.createBranchUser(branch);
			            
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
	@RequestMapping(value= "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAllBranch() {
		ResponseBean rb = new ResponseBean();
		try {

			
					List<Branchuser> list = branchuserservice.findAll();
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
	@RequestMapping(method = RequestMethod.GET, value= "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable int id, @PathVariable String uuid) {

		ResponseBean rb = new ResponseBean();
		try {

			
				
					Branchuser branchuser = branchuserservice.findByid(uuid);
					if (branchuser == null) {
						rb.setCode(Messages.NULLS_FOUND);
						rb.setDescription("The season was not found");
					} else {
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The branch is successfuly found");
						rb.setObject(branchuser);
					}

		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured please try again!!");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	
}
	
    @CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value= "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findbyusername(HttpServletRequest request, @PathVariable String username) {

		ResponseBean rb = new ResponseBean();
		try {

			
				
					Branchuser branchuser = branchuserservice.findByusername(username);
					if (branchuser == null) {
						rb.setCode(Messages.NULLS_FOUND);
						rb.setDescription("The season was not found");
					} else {
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The branch is successfuly found");
						rb.setObject(branchuser);
					}

		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured please try again!!");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	
}
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{uuid}")
	public ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable String uuid) {
        
		ResponseBean rb = new ResponseBean();
		try {

					Branchuser branch = branchuserservice.findByid(uuid);

					if (branch == null) {
						rb.setCode(Messages.NOT_FOUND);
						rb.setDescription("The branch you want  Delete does not exit");
					} else {

						branchuserservice.deleteBranchUser(branch.getId());
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
		Branchuser branch = new Branchuser();
		
		try {
			            
			            Registrar registrar = registrarservice.findByUuid(uuid);
			            branchuserservice.findByid(buuid);
			            branchuserservice.updateBranchUser(branch);
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
	
	

}
