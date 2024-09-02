package com.masterproject.AUTOtech.agil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.masterproject.AUTOtech.agil.dto.HardwarePropertyDto;
import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.service.HardwarePropertyService;


@RestController
@RequestMapping("/api/ecu/")
public class HardwarePropertyController {
	
	private HardwarePropertyService hardwarePropertyService;

	
    public HardwarePropertyController(HardwarePropertyService hardwarePropertyService) {
		super();
		this.hardwarePropertyService = hardwarePropertyService;
	}


	// Add Ecu Hardware REST API
    @PostMapping("{ecu_id}/hardware")
    public ResponseEntity<HardwarePropertyDto> addEcuHardware(@PathVariable(value="ecu_id") Long ecu_id, @RequestBody HardwarePropertyDto hardwarePropertyDto) {
        return new ResponseEntity<>(hardwarePropertyService.createEcuHardware(ecu_id, hardwarePropertyDto), HttpStatus.CREATED);
    }
    
    //Get All hardware REST API
    @GetMapping("{ecu_id}/hardwares")
    public ResponseEntity<List<HardwarePropertyDto>> getAllEcuHardware(@PathVariable(value="ecu_id") Long ecu_id){
    	List<HardwarePropertyDto> ecuHardware = hardwarePropertyService.getAllEcuHardwareByEcuId(ecu_id);
    	return ResponseEntity.ok(ecuHardware);
    }
    
	//Update Ecu hardware by Id REST API
	@PutMapping("hardware/{id}/update")
	public ResponseEntity<HardwarePropertyDto> updateEcuHardware(@RequestBody HardwarePropertyDto hardwarePropertyDto,@PathVariable("id") Long id) {
		HardwarePropertyDto response = hardwarePropertyService.updateEcuHardware(hardwarePropertyDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
	//Delete ECU hardware by Id REST API
	@DeleteMapping("hardware/{id}/delete")
	public ResponseEntity<String> deleteEcuHardwareId(@PathVariable("id") Long ecuId) {
		hardwarePropertyService.deleteEcuHardwareId(ecuId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
