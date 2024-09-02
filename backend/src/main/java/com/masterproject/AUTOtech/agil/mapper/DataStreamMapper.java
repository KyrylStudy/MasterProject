package com.masterproject.AUTOtech.agil.mapper;

import com.masterproject.AUTOtech.agil.dto.DataStreamDto;
import com.masterproject.AUTOtech.agil.entity.DataStream;

public class DataStreamMapper {
	
	public static DataStream mapToDataStream(DataStreamDto dataStreamDto) {
		DataStream dataStream = new DataStream();

		dataStream.setName(dataStreamDto.getName());
		dataStream.setType(dataStreamDto.getType());
		dataStream.setDescription(dataStreamDto.getDescription());
		dataStream.setPositionFromX(dataStreamDto.getPositionFromX());
		dataStream.setPositionFromY(dataStreamDto.getPositionFromY());
		dataStream.setPositionToX(dataStreamDto.getPositionToX());
		dataStream.setPositionToY(dataStreamDto.getPositionToY());
		dataStream.setConnectedFrom(dataStreamDto.getConnectedFrom());
		dataStream.setConnectedTo(dataStreamDto.getConnectedTo());
		dataStream.setTwoWayConnection(dataStreamDto.getTwoWayConnection());

		
		return dataStream;
	}
	
	public static DataStreamDto mapToDataStreamDto(DataStream dataStream) {
		DataStreamDto dataStreamDto = new DataStreamDto();
		
		dataStreamDto.setId(dataStream.getId());
		dataStreamDto.setName(dataStream.getName());
		dataStreamDto.setType(dataStream.getType());
		dataStreamDto.setDescription(dataStream.getDescription());
		dataStreamDto.setPositionFromX(dataStream.getPositionFromX());
		dataStreamDto.setPositionFromY(dataStream.getPositionFromY());
		dataStreamDto.setPositionToX(dataStream.getPositionToX());
		dataStreamDto.setPositionToY(dataStream.getPositionToY());
		dataStreamDto.setConnectedFrom(dataStream.getConnectedFrom());
		dataStreamDto.setConnectedTo(dataStream.getConnectedTo());
		dataStreamDto.setTwoWayConnection(dataStream.getTwoWayConnection());

			
		return dataStreamDto;
	}

}

