package com.masterproject.AUTOtech.agil.mapper;

import com.masterproject.AUTOtech.agil.dto.HardwarePropertyDto;
import com.masterproject.AUTOtech.agil.entity.HardwareProperty;

public class HardwarePropertyMapper {
	
	public static HardwareProperty mapToHardwareProperty(HardwarePropertyDto hardwarePropertyDto) {
		HardwareProperty hardwareProperty = new HardwareProperty();
		hardwareProperty.setId(hardwarePropertyDto.getId());
		hardwareProperty.setName(hardwarePropertyDto.getName());
		hardwareProperty.setValue(hardwarePropertyDto.getValue());		
		
		return hardwareProperty;
	}
	
	public static HardwarePropertyDto mapToHardwarePropertyDto(HardwareProperty hardwareProperty) {
		HardwarePropertyDto hardwarePropertyDto = new HardwarePropertyDto();
		hardwarePropertyDto.setId(hardwareProperty.getId());
		hardwarePropertyDto.setName(hardwareProperty.getName());
		hardwarePropertyDto.setValue(hardwareProperty.getValue());
			
		return hardwarePropertyDto;
	}

}
