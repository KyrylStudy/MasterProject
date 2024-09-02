package com.masterproject.AUTOtech.agil.mapper;

import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.entity.Hardware;

public class HardwareMapper {
	
	public static Hardware mapToHardware(HardwareDto hardwareDto) {
		Hardware hardware = new Hardware();
			//ecuDto.getId(),
			hardware.setLabel(hardwareDto.getLabel());
			hardware.setType(hardwareDto.getType());
			hardware.setDescription(hardwareDto.getDescription());
			hardware.setPositionX(hardwareDto.getPositionX());
			hardware.setPositionY(hardwareDto.getPositionY());
			hardware.setConnectedTo(hardwareDto.getConnectedTo());
			
		
		return hardware;
		
	}
	
	public static HardwareDto mapToHardwareDto(Hardware hardware) {
		HardwareDto hardwareDto = new HardwareDto();
			hardwareDto.setId(hardware.getId());
			hardwareDto.setLabel(hardware.getLabel());
			hardwareDto.setType(hardware.getType());
			hardwareDto.setDescription(hardware.getDescription());
			hardwareDto.setPositionX(hardware.getPositionX());
			hardwareDto.setPositionY(hardware.getPositionY());
			hardwareDto.setConnectedTo(hardware.getConnectedTo());
			
		
			
		return hardwareDto;
	}

}
