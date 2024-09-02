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

import com.masterproject.AUTOtech.agil.dto.DataStreamPropertyDto;
import com.masterproject.AUTOtech.agil.service.DataStreamPropertyService;



@RestController
@RequestMapping("/api/")
public class DataStreamPropertyController {

    private DataStreamPropertyService dataStreamPropertyService;

    public DataStreamPropertyController(DataStreamPropertyService dataStreamPropertyService) {
        super();
        this.dataStreamPropertyService = dataStreamPropertyService;
    }

    // Add Bus Property REST API
    @PostMapping("data-stream_property/{data_stream_id}")
    public ResponseEntity<DataStreamPropertyDto> addDataStreamProperty(@PathVariable(value="data_stream_id") Long data_stream_id, @RequestBody DataStreamPropertyDto dataStreamPropertyDto) {
        return new ResponseEntity<>(dataStreamPropertyService.createDataStreamProperty(data_stream_id, dataStreamPropertyDto), HttpStatus.CREATED);
    }

    // Get EcuSoftware REST API
    /*@GetMapping("/{id}")
    public ResponseEntity<EcuSoftwareDto> getEcuSoftwareById(@PathVariable Long id) {
        EcuSoftwareDto ecuSoftwareDto = ecuSoftwareService.getEcuSoftwareDtoById(id);
        return ResponseEntity.ok(ecuSoftwareDto);
    }*/
    
    //Get All Bus Properties REST API
    @GetMapping("data-stream_property/get-all/{data_stream_id}")
    public ResponseEntity<List<DataStreamPropertyDto>> getAllDataStreamPropertyByDataStreamId(@PathVariable(value="data_stream_id") Long data_stream_id){
    	List<DataStreamPropertyDto> dataStreamProperty = dataStreamPropertyService.getAllDataStreamPropertyByDataStreamId(data_stream_id);
    	return ResponseEntity.ok(dataStreamProperty);
    }
    
	//Update Bus Property by Id REST API
	@PutMapping("data-stream_property/{id}/update")
	public ResponseEntity<DataStreamPropertyDto> updateDataStreamProperty(@RequestBody DataStreamPropertyDto dataStreamPropertyDto,@PathVariable("id") Long id) {
		DataStreamPropertyDto response = dataStreamPropertyService.updateDataStreamProperty(dataStreamPropertyDto, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
	//Delete Bus Property by Id REST API
	@DeleteMapping("data-stream_property/{id}/delete")
	public ResponseEntity<String> deleteDataStreamPropertyId(@PathVariable("id") Long id) {
		dataStreamPropertyService.deleteDataStreamPropertyId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


