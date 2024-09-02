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

import com.masterproject.AUTOtech.agil.dto.DataStreamDto;
import com.masterproject.AUTOtech.agil.service.DataStreamService;

@RestController
@RequestMapping("/api/data-stream")
public class DataStreamController {
	
	private DataStreamService dataStreamService;
	
	public DataStreamController(DataStreamService dataStreamService) {
		super();
		this.dataStreamService = dataStreamService;
	}
	
	//Add ECU REST API
	@PostMapping("/{architecture_id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DataStreamDto> createDataStream(@PathVariable(value="architecture_id") Long architecture_id, @RequestBody DataStreamDto dataStreamDto){
		return new ResponseEntity<>(dataStreamService.createDataStream(architecture_id, dataStreamDto), HttpStatus.CREATED);
	}
	
	//Get all Architecture REST API
	@GetMapping("/architecture/{architecture_id}")
	public ResponseEntity<List<DataStreamDto>> getAllDataStreams(@PathVariable(value="architecture_id") Long architecture_id){
		return new ResponseEntity<>(dataStreamService.getAllDataStreams(architecture_id), HttpStatus.OK);
	}
	
	//Update Architecture by Id REST API
	@PutMapping("/{id}/update")
	public ResponseEntity<DataStreamDto> updateDataStream(@RequestBody DataStreamDto dataStreamDto,@PathVariable("id") Long id) {
		DataStreamDto response = dataStreamService.updateDataStream(dataStreamDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//Delete Architecture by Id REST API
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteDataStreamId(@PathVariable("id") Long id) {
		dataStreamService.deleteDataStreamId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

