package com.Solar.SolarEnergy.Controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Solar.SolarEnergy.Service.RegistrarService;
import com.Solar.SolarEnergy.Service.SolarEnergyService;

@RestController
@CrossOrigin
@RequestMapping("/pictures")
public class ImageController{

	
	@Autowired
	private SolarEnergyService solarenergyservice;
	
	
	@Autowired
	private RegistrarService registrarsevice;
	
	
	@GetMapping(value = "/{option}/{uuid}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("uuid")String uuid,@PathVariable("option")String option) throws IOException {
		String picPath="";
		
		if(option.equalsIgnoreCase("se")){
		picPath=solarenergyservice.findByUuid(uuid).getFile();
		}
		
		String workingDir = System.getProperty("user.dir");
		String filePath = workingDir + "/"+picPath.replaceAll("\\\\", "/");  
		
		File file = new File(filePath);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("Content-Disposition", "inline; filename=" + file.getName());
		Path path = Paths.get(filePath);
		byte[] b = Files.readAllBytes((path));
		ByteArrayInputStream bis = new ByteArrayInputStream(b);

		// Files.rea
		//InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(bis)); 
    }	
	
}
