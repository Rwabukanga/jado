package com.Solar.SolarEnergy.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;

import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Domain.Solarenergy;
import com.Solar.SolarEnergy.Service.BranchService;
import com.Solar.SolarEnergy.Service.SolarEnergyService;
import com.Solar.SolarEnergy.Utitlity.Messages;
import com.Solar.SolarEnergy.Utitlity.ResponseBean;

@RestController
@CrossOrigin
@RequestMapping(value= "/solarenergy")
public class SolarEnergyController {

	@Autowired
	private SolarEnergyService solarenergyservice;
	
	
	@Autowired
	private BranchService bservice;

	@CrossOrigin
	@RequestMapping(value= "/save", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createSolarEnergy(HttpServletRequest request, @RequestBody Solarenergy solarenergy) {
		ResponseBean rb = new ResponseBean();
		
	
		try {
			
			            solarenergyservice.createSolarenergy(solarenergy);
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(solarenergy);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			rb.setObject(solarenergy);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value= "/savesolar/{buuid}", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createSolarEnergytobranch(HttpServletRequest request, @RequestBody Solarenergy solarenergy,@PathVariable  String buuid ) {
		ResponseBean rb = new ResponseBean();
		
		try {
			
			 
			    Branch br =  bservice.findByUuid(buuid);
			    solarenergy.setBranch(br);
			    solarenergyservice.createSolarenergy(solarenergy);
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			rb.setObject(solarenergy);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<Object> event_self_reg(HttpServletRequest request,@RequestParam Map<String, String> params, HttpSession session,@RequestParam(name = "attachement-0", required = false) MultipartFile file) {
	//ResponseBean rb = new ResponseBean();
	ResponseBean responseBean=new ResponseBean();
		try {
			
			
			System.out.println("called++++++======");
			
					
					String filePath = "";
				      String upladFilePath = "";
				      if (file != null) {
				        // Get length of file in bytes
				        long fileSizeInBytes = file.getSize();
				        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
				        double fileSizeInKB = fileSizeInBytes / 1024;
				        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
				        double fileSizeInMB = fileSizeInKB / 1024;

				        if (fileSizeInMB <= 5) {
				    
				        	// saving product
				    Solarenergy en=new Solarenergy();
				    String branchuuid= params.get("branchid");
				    Branch e= bservice.findByUuid(branchuuid);
				    en.setQuantity(Integer.parseInt(params.get("quantity")));
				    en.setDescription(params.get("description")); 
				    en.setSolarquality(params.get("quality"));
				    en.setSolarcapacity(params.get("capacity"));
				    en.setCost(Double.parseDouble(params.get("cost")));
				    en.setBranch(e);
				 /*   en.setFile(params.get("file"));*/
				   
				   solarenergyservice.createSolarenergy(en);
				   
					 
				          upladFilePath = en.getId() + "-"
				              + file.getOriginalFilename().replaceAll("\\s", "_");
				          filePath ="solarenergy/Main_Photos/";
				          File f = new File(filePath);
				          if (!f.exists()) {
				            f.mkdirs();
				          }
				          byte[] bytes = file.getBytes();
				          Path path = Paths.get(filePath + "/" + upladFilePath);
				          Files.write(path, bytes);
				          en.setFile(path.toString()); 
				          solarenergyservice.updateSolarenergy(en);	
				           
				           responseBean.setCode(Messages.SUCCESS_CODE);
				           responseBean.setDescription("PRODUCT IS SUCCESSFULY SAVED ");   
				           responseBean.setObject(en);
				           
				      }else{
				        responseBean.setCode(Messages.ERROR_CODE);
				        responseBean.setDescription("The Photo Exceeds permitted size");
				        responseBean.setObject(null);
				      }
				      
				    }else{
				      responseBean.setCode(Messages.ERROR_CODE);
				      responseBean.setDescription("Please Attach Photo");
				      responseBean.setObject(null);
				    }
				  
			
		    }catch (Exception e) {
		      
		      responseBean.setCode(Messages.ERROR_CODE);
		      responseBean.setDescription(Messages.error);
		      responseBean.setObject(null);
		      
		    }
		    
		    return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
		
	}
	
	
	
	
	@CrossOrigin
	@RequestMapping(value= "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAllSolarEnergy() {
		ResponseBean rb = new ResponseBean();
		try {


					List<Solarenergy> list = solarenergyservice.findAll();
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("The solarenergy are successfuly retrieved");
					rb.setObject(list);


		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured while retrieving solarenergy");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable int id) {

		ResponseBean rb = new ResponseBean();
		try {

			
				
					Solarenergy solar=solarenergyservice.findByid(id);
					if (solar == null) {
						rb.setCode(Messages.NULLS_FOUND);
						rb.setDescription("The season was not found");
					} else {
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The branch is successfuly found");
						rb.setObject(solar);
					}

		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured please try again!!");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	
}
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable int id) {
        
		ResponseBean rb = new ResponseBean();
		try {

					Solarenergy solar = solarenergyservice.findByid(id);

					if (solar == null) {
						rb.setCode(Messages.NOT_FOUND);
						rb.setDescription("The Season you want  Delete does not exit");
					} else {

						solarenergyservice.deleteSolarenergy(id);
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
	
}
