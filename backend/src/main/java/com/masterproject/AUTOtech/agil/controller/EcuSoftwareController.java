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
import com.masterproject.AUTOtech.agil.service.EcuSoftwareService;

@RestController
@RequestMapping("/api/")
public class EcuSoftwareController {

    private EcuSoftwareService ecuSoftwareService;

    public EcuSoftwareController(EcuSoftwareService ecuSoftwareService) {
        super();
        this.ecuSoftwareService = ecuSoftwareService;
    }

    // Add EcuSoftware REST API
    @PostMapping("/ecus/{ecu_id}/software")
    public ResponseEntity<EcuSoftwareDto> addEcuSoftware(@PathVariable(value="ecu_id") Long ecu_id, @RequestBody EcuSoftwareDto ecuSoftwareDto) {
        return new ResponseEntity<>(ecuSoftwareService.createEcuSoftwareDto(ecu_id, ecuSoftwareDto), HttpStatus.CREATED);
    }

    // Get EcuSoftware REST API
    /*@GetMapping("/{id}")
    public ResponseEntity<EcuSoftwareDto> getEcuSoftwareById(@PathVariable Long id) {
        EcuSoftwareDto ecuSoftwareDto = ecuSoftwareService.getEcuSoftwareDtoById(id);
        return ResponseEntity.ok(ecuSoftwareDto);
    }*/
    
    //Get All Softwares REST API
    @GetMapping("/ecus/{ecu_id}/softwares")
    public ResponseEntity<List<EcuSoftwareDto>> getAllEcuSoftware(@PathVariable(value="ecu_id") Long ecu_id){
    	List<EcuSoftwareDto> ecuSoftware = ecuSoftwareService.getAllEcuSoftwareByEcuId(ecu_id);
    	return ResponseEntity.ok(ecuSoftware);
    }
    
	//Update Ecu Software by Id REST API
	@PutMapping("software/{id}/update")
	public ResponseEntity<EcuSoftwareDto> updateEcuSoftware(@RequestBody EcuSoftwareDto ecuSoftwareDto,@PathVariable("id") Long id) {
		EcuSoftwareDto response = ecuSoftwareService.updateEcuSoftware(ecuSoftwareDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
	//Delete ECU Software by Id REST API
	@DeleteMapping("software/{id}/delete")
	public ResponseEntity<String> deleteEcuSoftwareId(@PathVariable("id") Long ecuId) {
		ecuSoftwareService.deleteEcuSoftwareId(ecuId);
		return new ResponseEntity<>("ECU Software deleted!", HttpStatus.OK);
	}
}

