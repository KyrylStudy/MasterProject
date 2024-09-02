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

import com.masterproject.AUTOtech.agil.dto.ConnectionPropertyDto;
import com.masterproject.AUTOtech.agil.service.ConnectionPropertyService;



@RestController
@RequestMapping("/api/")
public class ConnectionPropertyController {

    private ConnectionPropertyService connectionPropertyService;

    public ConnectionPropertyController(ConnectionPropertyService connectionPropertyService) {
        super();
        this.connectionPropertyService = connectionPropertyService;
    }

    // Add Bus Property REST API
    @PostMapping("bus_property/{bus_id}")
    public ResponseEntity<ConnectionPropertyDto> addBusProperty(@PathVariable(value="bus_id") Long bus_id, @RequestBody ConnectionPropertyDto connectionPropertyDto) {
        return new ResponseEntity<>(connectionPropertyService.createBusProperty(bus_id, connectionPropertyDto), HttpStatus.CREATED);
    }

    // Get EcuSoftware REST API
    /*@GetMapping("/{id}")
    public ResponseEntity<EcuSoftwareDto> getEcuSoftwareById(@PathVariable Long id) {
        EcuSoftwareDto ecuSoftwareDto = ecuSoftwareService.getEcuSoftwareDtoById(id);
        return ResponseEntity.ok(ecuSoftwareDto);
    }*/
    
    //Get All Bus Properties REST API
    @GetMapping("bus_properties/{bus_id}")
    public ResponseEntity<List<ConnectionPropertyDto>> getAllBusProperties(@PathVariable(value="bus_id") Long bus_id){
    	List<ConnectionPropertyDto> busProperty = connectionPropertyService.getAllBusPropertyByBusId(bus_id);
    	return ResponseEntity.ok(busProperty);
    }
    
	//Update Bus Property by Id REST API
	@PutMapping("bus_property/{id}/update")
	public ResponseEntity<ConnectionPropertyDto> updateBusProperty(@RequestBody ConnectionPropertyDto connectionPropertyDto,@PathVariable("id") Long id) {
		ConnectionPropertyDto response = connectionPropertyService.updateBusProperty(connectionPropertyDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
	//Delete Bus Property by Id REST API
	@DeleteMapping("bus_property/{id}/delete")
	public ResponseEntity<String> deleteBusPropertyId(@PathVariable("id") Long id) {
		connectionPropertyService.deleteBusPropertyId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


