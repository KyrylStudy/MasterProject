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
import com.masterproject.AUTOtech.agil.service.HardwareService;

@RestController
@RequestMapping("/api/ecus")
public class HardwareController {

	private HardwareService hardwareService;

	public HardwareController(HardwareService hardwareService) {
		super();
		this.hardwareService = hardwareService;
	}
	
	//Add ECU REST API
	@PostMapping("/{architecture_id}/ecu")
	public ResponseEntity<HardwareDto> createECU(@PathVariable(value="architecture_id") Long architecture_id, @RequestBody HardwareDto ecuDto){
		return new ResponseEntity<>(hardwareService.createECU(architecture_id, ecuDto), HttpStatus.CREATED);
	}
	
	//Get all ECUs by architecture id REST API
	@GetMapping("/architecture/{architecture_id}")
	public ResponseEntity<List<HardwareDto>> getAllEcusByArchitectureId(@PathVariable(value="architecture_id") Long architecture_id){
		return new ResponseEntity<>(hardwareService.getAllEcusByArchitectureId(architecture_id), HttpStatus.OK);
	}
	
	//Get ECU by Id REST API
	@GetMapping("/{id}")
	public ResponseEntity<HardwareDto> getECUbyId(@PathVariable Long id) {
		return ResponseEntity.ok(hardwareService.getECUbyId(id));
	}

	//Update ECU by Id REST API
	@PutMapping("/{id}/update")
	public ResponseEntity<HardwareDto> updateEcu(@RequestBody HardwareDto ecuDto,@PathVariable("id") Long ecuId) {
		HardwareDto response = hardwareService.updateEcu(ecuDto, ecuId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//Delete ECU by Id REST API
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteEcuId(@PathVariable("id") Long ecuId) {
		hardwareService.deleteEcuId(ecuId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}
