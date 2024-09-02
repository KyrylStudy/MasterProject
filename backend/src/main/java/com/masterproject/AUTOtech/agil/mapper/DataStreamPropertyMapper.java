package com.masterproject.AUTOtech.agil.mapper;

import com.masterproject.AUTOtech.agil.dto.DataStreamPropertyDto;
import com.masterproject.AUTOtech.agil.entity.DataStreamProperty;

public class DataStreamPropertyMapper {
	
	public static DataStreamProperty mapToDataStreamProperty(DataStreamPropertyDto dataStreamPropertyDto) {
		DataStreamProperty dataStreamProperty = new DataStreamProperty();
		dataStreamProperty.setId(dataStreamPropertyDto.getId());
		dataStreamProperty.setName(dataStreamPropertyDto.getName());
		dataStreamProperty.setValue(dataStreamPropertyDto.getValue());		
		
		return dataStreamProperty;
	}
	
	
	public static DataStreamPropertyDto mapToDataStreamPropertyDto(DataStreamProperty dataStreamProperty) {
		DataStreamPropertyDto dataStreamPropertyDto = new DataStreamPropertyDto();
		dataStreamPropertyDto.setId(dataStreamProperty.getId());
		dataStreamPropertyDto.setName(dataStreamProperty.getName());
		dataStreamPropertyDto.setValue(dataStreamProperty.getValue());
			
		return dataStreamPropertyDto;
		
	}

}


