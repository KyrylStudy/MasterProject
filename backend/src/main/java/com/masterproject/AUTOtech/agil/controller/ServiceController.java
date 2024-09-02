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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.dto.ServiceDto;
import com.masterproject.AUTOtech.agil.service.HardwareService;
import com.masterproject.AUTOtech.agil.service.ServiceService;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

	private ServiceService serviceService;

	public ServiceController(ServiceService serviceService) {
		super();
		this.serviceService = serviceService;
	}
	
	//Add ECU REST API
	@PostMapping("/{ecu_id}")
	public ResponseEntity<ServiceDto> createECU(@PathVariable(value="ecu_id") Long ecu_id, @RequestBody ServiceDto serviceDto){
		return new ResponseEntity<>(serviceService.createService(ecu_id, serviceDto), HttpStatus.CREATED);
	}
	
	//Get all ECUs by architecture id REST API
	@GetMapping("/ecu/{ecu_id}")
	public ResponseEntity<List<ServiceDto>> getAllServicesByEcuId(@PathVariable(value="ecu_id") Long ecu_id){
		return new ResponseEntity<>(serviceService.getAllServicesByEcuId(ecu_id), HttpStatus.OK);
	}
	
	//Get ECU by Id REST API
	/*@GetMapping("/{id}")
	public ResponseEntity<ECUdto> getECUbyId(@PathVariable Long id) {
		return ResponseEntity.ok(ecuService.getECUbyId(id));
	}*/

	//Update ECU by Id REST API
	@PutMapping("/{id}/update")
	public ResponseEntity<ServiceDto> updateService(@RequestBody ServiceDto serviceDto,@PathVariable("id") Long id) {
		ServiceDto response = serviceService.updateService(serviceDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//Delete ECU by Id REST API
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteServiceId(@PathVariable("id") Long id) {
		serviceService.deleteServiceId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}

