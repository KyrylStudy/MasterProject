package com.masterproject.AUTOtech.agil.mapper;

import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.entity.EcuSoftware;

public class EcuSoftwareMapper {
	
	public static EcuSoftware mapToEcuSoftware(EcuSoftwareDto ecuSoftwareDto) {
		EcuSoftware ecuSoftware = new EcuSoftware();
		ecuSoftware.setId(ecuSoftwareDto.getId());
		ecuSoftware.setName(ecuSoftwareDto.getName());
		ecuSoftware.setValue(ecuSoftwareDto.getValue());		
		
		return ecuSoftware;
	}
	
	public static EcuSoftwareDto mapToEcuSoftwareDto(EcuSoftware ecuSoftware) {
		EcuSoftwareDto ecuSoftwareDto = new EcuSoftwareDto();
		ecuSoftwareDto.setId(ecuSoftware.getId());
		ecuSoftwareDto.setName(ecuSoftware.getName());
		ecuSoftwareDto.setValue(ecuSoftware.getValue());
			
		return ecuSoftwareDto;
	}

}

