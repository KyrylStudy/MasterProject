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

import com.masterproject.AUTOtech.agil.dto.ArchitectureDto;
import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.service.ArchitectureService;

@RestController
@RequestMapping("/api/architecture")
public class ArchitectureController {
	
	private ArchitectureService architectureService;
	
	public ArchitectureController(ArchitectureService architectureService) {
		super();
		this.architectureService = architectureService;
	}
	
	//Add Architecture REST API
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ArchitectureDto> createArchitecture(@RequestBody ArchitectureDto architectureDto){
		return new ResponseEntity<>(architectureService.createArchitecture(architectureDto), HttpStatus.CREATED);
	}
	
	//Get all Architecture REST API
	@GetMapping
	public ResponseEntity<List<ArchitectureDto>> getAllArchitecture(){
		return new ResponseEntity<>(architectureService.getAllArchitecture(), HttpStatus.OK);
	}
	
	//Get Architecture by Id REST API
	@GetMapping("/{id}")
	public ResponseEntity<ArchitectureDto> getArchitectureById(@PathVariable Long id) {
		return ResponseEntity.ok(architectureService.getArchitectureById(id));
	}
	
	//Update Architecture by Id REST API
	@PutMapping("/{id}/update")
	public ResponseEntity<ArchitectureDto> updateArchitecture(@RequestBody ArchitectureDto architectureDto,@PathVariable("id") Long architectureId) {
		ArchitectureDto response = architectureService.updateArchitecture(architectureDto, architectureId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//Delete Architecture by Id REST API
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteArchitectureId(@PathVariable("id") Long architectureId) {
		architectureService.deleteArchitectureId(architectureId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
