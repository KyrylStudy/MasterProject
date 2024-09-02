package com.masterproject.AUTOtech.agil.service;

import java.util.List;

import com.masterproject.AUTOtech.agil.dto.DataStreamPropertyDto;

public interface DataStreamPropertyService {
	
	DataStreamPropertyDto createDataStreamProperty(Long data_stream_id, DataStreamPropertyDto dataStreamPropertyDto);
	
	List<DataStreamPropertyDto> getAllDataStreamPropertyByDataStreamId(Long data_stream_id);
	
	DataStreamPropertyDto updateDataStreamProperty(DataStreamPropertyDto dataStreamPropertyDto, Long id);
	
	void deleteDataStreamPropertyId(Long id);
	
	//EcuSoftwareDto getEcuSoftwareDtoById(Long id);

}

