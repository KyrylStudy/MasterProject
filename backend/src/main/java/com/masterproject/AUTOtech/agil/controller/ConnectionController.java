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

import com.masterproject.AUTOtech.agil.dto.ConnectionDto;
import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.service.ConnectionService;

@RestController
@RequestMapping("/api/bus")
public class ConnectionController {
	
	private ConnectionService connectionService;
	
	public ConnectionController(ConnectionService connectionService) {
		super();
		this.connectionService = connectionService;
	}
	
	//Add ECU REST API
	@PostMapping("/{architecture_id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ConnectionDto> createBus(@PathVariable(value="architecture_id") Long architecture_id, @RequestBody ConnectionDto connectionDto){
		return new ResponseEntity<>(connectionService.createBus(architecture_id, connectionDto), HttpStatus.CREATED);
	}
	
	//Get all Architecture REST API
	@GetMapping("/architecture/{architecture_id}")
	public ResponseEntity<List<ConnectionDto>> getAllBuses(@PathVariable(value="architecture_id") Long architecture_id){
		return new ResponseEntity<>(connectionService.getAllBuses(architecture_id), HttpStatus.OK);
	}
	
	//Update Architecture by Id REST API
	@PutMapping("/{id}/update")
	public ResponseEntity<ConnectionDto> updateArchitecture(@RequestBody ConnectionDto connectionDto,@PathVariable("id") Long id) {
		ConnectionDto response = connectionService.updateBus(connectionDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//Delete Architecture by Id REST API
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteBusId(@PathVariable("id") Long id) {
		connectionService.deleteBusId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
