package com.masterproject.AUTOtech.agil.service;

import java.util.List;

import com.masterproject.AUTOtech.agil.dto.ConnectionPropertyDto;

public interface ConnectionPropertyService {
	
	ConnectionPropertyDto createBusProperty(Long bus_id, ConnectionPropertyDto connectionPropertyDto);
	
	//EcuSoftwareDto getEcuSoftwareDtoById(Long id);
	
	List<ConnectionPropertyDto> getAllBusPropertyByBusId(Long bus_id);
	
	ConnectionPropertyDto updateBusProperty(ConnectionPropertyDto connectionPropertyDto, Long id);
	
	void deleteBusPropertyId(Long id);

}

