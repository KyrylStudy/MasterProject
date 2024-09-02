package com.masterproject.AUTOtech.agil.service;

import java.util.List;

import com.masterproject.AUTOtech.agil.dto.ConnectionDto;

public interface ConnectionService {
	
	ConnectionDto createBus(Long architecture_id, ConnectionDto connectionDto);
	
	List<ConnectionDto> getAllBuses(Long architecture_id);
	
	//ECUdto getECUbyId(Long id);---------
	
	ConnectionDto updateBus(ConnectionDto connectionDto, Long id);
	
	void deleteBusId(Long id);

}
