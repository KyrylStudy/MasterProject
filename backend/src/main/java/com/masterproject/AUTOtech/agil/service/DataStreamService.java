package com.masterproject.AUTOtech.agil.service;

import java.util.List;

import com.masterproject.AUTOtech.agil.dto.DataStreamDto;

public interface DataStreamService {
	
	DataStreamDto createDataStream(Long architecture_id, DataStreamDto dataStreamDto);
	
	List<DataStreamDto> getAllDataStreams(Long architecture_id);
	
	DataStreamDto updateDataStream(DataStreamDto dataStreamDto, Long id);
	
	void deleteDataStreamId(Long id);
	
	//ECUdto getECUbyId(Long id);---------

}

