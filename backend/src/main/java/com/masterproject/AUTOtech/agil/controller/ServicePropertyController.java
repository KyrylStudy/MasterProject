package com.masterproject.AUTOtech.agil.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.dto.ServicePropertyDto;
import com.masterproject.AUTOtech.agil.service.EcuSoftwareService;
import com.masterproject.AUTOtech.agil.service.ServicePropertyService;

@RestController
@RequestMapping("/api/")
public class ServicePropertyController {

    private ServicePropertyService servicePropertyService;

    public ServicePropertyController(ServicePropertyService servicePropertyService) {
        super();
        this.servicePropertyService = servicePropertyService;
    }

    // Add EcuSoftware REST API
    @PostMapping("service_property/{service_id}")
    public ResponseEntity<ServicePropertyDto> addServiceProperty(@PathVariable(value="service_id") Long service_id, @RequestBody ServicePropertyDto servicePropertyDto) {
        return new ResponseEntity<>(servicePropertyService.createServiceProperty(service_id, servicePropertyDto), HttpStatus.CREATED);
    }

    // Get EcuSoftware REST API
    /*@GetMapping("/{id}")
    public ResponseEntity<EcuSoftwareDto> getEcuSoftwareById(@PathVariable Long id) {
        EcuSoftwareDto ecuSoftwareDto = ecuSoftwareService.getEcuSoftwareDtoById(id);
        return ResponseEntity.ok(ecuSoftwareDto);
    }*/
    
    //Get All Softwares REST API
    @GetMapping("service_properties/{service_id}")
    public ResponseEntity<List<ServicePropertyDto>> getAllServiceProperties(@PathVariable(value="service_id") Long service_id){
    	List<ServicePropertyDto> servicePropertyDto = servicePropertyService.getAllServicePropertiesByServiceId(service_id);
    	return ResponseEntity.ok(servicePropertyDto);
    }
    
	//Update Ecu Software by Id REST API
	@PutMapping("service_property/{id}/update")
	public ResponseEntity<ServicePropertyDto> updateServiceProperty(@RequestBody ServicePropertyDto servicePropertyDto,@PathVariable("id") Long id) {
		ServicePropertyDto response = servicePropertyService.updateServiceProperty(servicePropertyDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
	//Delete ECU Software by Id REST API
	@DeleteMapping("service_property/{id}/delete")
	public ResponseEntity<String> deleteServiceProperty(@PathVariable("id") Long id) {
		servicePropertyService.deleteServiceProperty(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


