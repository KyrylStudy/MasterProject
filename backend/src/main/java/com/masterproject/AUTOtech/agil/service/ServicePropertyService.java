package com.masterproject.AUTOtech.agil.service;

import java.util.List;

import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.dto.ServicePropertyDto;

public interface ServicePropertyService {
	
	ServicePropertyDto createServiceProperty(Long service_id, ServicePropertyDto servicePropertyDto);
	
	//EcuSoftwareDto getEcuSoftwareDtoById(Long id);
	
	List<ServicePropertyDto> getAllServicePropertiesByServiceId(Long service_id);
	
	ServicePropertyDto updateServiceProperty(ServicePropertyDto servicePropertyDto, Long id);
	
	void deleteServiceProperty(Long id);

}
