package com.masterproject.AUTOtech.agil.mapper;


import com.masterproject.AUTOtech.agil.dto.ServiceDto;
import com.masterproject.AUTOtech.agil.entity.ServiceEntity;

public class ServiceMapper {
	
	public static ServiceEntity mapToService(ServiceDto serviceDto) {
		ServiceEntity serviceEntity = new ServiceEntity();
		
		serviceEntity.setName(serviceDto.getName());
		serviceEntity.setType(serviceDto.getType());
		serviceEntity.setDescription(serviceDto.getDescription());
		serviceEntity.setPositionX(serviceDto.getPositionX());
		serviceEntity.setPositionY(serviceDto.getPositionY());
		serviceEntity.setConnectedTo(serviceDto.getConnectedTo());
		//serviceEntity.setParent_ecu_id(serviceDto.getParent_ecu_id());
			
		return serviceEntity;
	}
	
	public static ServiceDto mapToServiceDto(ServiceEntity serviceEntity) {
		ServiceDto serviceDto = new ServiceDto();
		serviceDto.setId(serviceEntity.getId());
		serviceDto.setName(serviceEntity.getName());
		serviceDto.setType(serviceEntity.getType());
		serviceDto.setDescription(serviceEntity.getDescription());
		serviceDto.setPositionX(serviceEntity.getPositionX());
		serviceDto.setPositionY(serviceEntity.getPositionY());
		serviceDto.setConnectedTo(serviceEntity.getConnectedTo());
		//serviceDto.setParent_ecu_id(serviceEntity.getParent_ecu_id());
			
		return serviceDto;
	}

}
