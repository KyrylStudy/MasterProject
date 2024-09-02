package com.masterproject.AUTOtech.agil.mapper;

import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.dto.ServicePropertyDto;
import com.masterproject.AUTOtech.agil.entity.EcuSoftware;
import com.masterproject.AUTOtech.agil.entity.ServiceProperty;

public class ServicePropertyMapper {
	
	public static ServiceProperty mapToServiceProperty(ServicePropertyDto servicePropertyDto) {
		ServiceProperty serviceProperty = new ServiceProperty();
		serviceProperty.setId(servicePropertyDto.getId());
		serviceProperty.setName(servicePropertyDto.getName());
		serviceProperty.setValue(servicePropertyDto.getValue());		
		
		return serviceProperty;
	}
	
	public static ServicePropertyDto mapToServicePropertyDto(ServiceProperty serviceProperty) {
		ServicePropertyDto servicePropertyDto = new ServicePropertyDto();
		servicePropertyDto.setId(serviceProperty.getId());
		servicePropertyDto.setName(serviceProperty.getName());
		servicePropertyDto.setValue(serviceProperty.getValue());
			
		return servicePropertyDto;
	}

}


