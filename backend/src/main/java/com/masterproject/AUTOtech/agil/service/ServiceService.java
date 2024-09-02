package com.masterproject.AUTOtech.agil.service;

import java.util.List;

//import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.dto.ServiceDto;
import com.masterproject.AUTOtech.agil.entity.Hardware;

//@Service
public interface ServiceService {
	
	ServiceDto createService(Long ecu_id, ServiceDto serviceDto);
	
	List<ServiceDto> getAllServicesByEcuId(Long ecu_id);
	
	//ECUdto getECUbyId(Long id);
	
	ServiceDto updateService(ServiceDto serviceDto, Long id);
	
	void deleteServiceId(Long id);
	
	

}

