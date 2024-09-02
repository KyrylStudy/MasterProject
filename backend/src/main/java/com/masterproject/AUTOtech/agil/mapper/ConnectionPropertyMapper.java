package com.masterproject.AUTOtech.agil.mapper;

import com.masterproject.AUTOtech.agil.dto.ConnectionPropertyDto;
import com.masterproject.AUTOtech.agil.entity.ConnectionProperty;

public class ConnectionPropertyMapper {
	
	public static ConnectionProperty mapToConnectionProperty(ConnectionPropertyDto connectionPropertyDto) {
		ConnectionProperty connectionProperty = new ConnectionProperty();
		connectionProperty.setId(connectionPropertyDto.getId());
		connectionProperty.setName(connectionPropertyDto.getName());
		connectionProperty.setValue(connectionPropertyDto.getValue());		
		
		return connectionProperty;
	}
	
	public static ConnectionPropertyDto mapToConnectionPropertyDto(ConnectionProperty connectionProperty) {
		ConnectionPropertyDto connectionPropertyDto = new ConnectionPropertyDto();
		connectionPropertyDto.setId(connectionProperty.getId());
		connectionPropertyDto.setName(connectionProperty.getName());
		connectionPropertyDto.setValue(connectionProperty.getValue());
			
		return connectionPropertyDto;
	}

}

