package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.ArchitectureDto;
import com.masterproject.AUTOtech.agil.dto.DataStreamDto;
import com.masterproject.AUTOtech.agil.entity.Architecture;
import com.masterproject.AUTOtech.agil.entity.DataStream;
import com.masterproject.AUTOtech.agil.exceprions.ArchitectureNotFound;
import com.masterproject.AUTOtech.agil.exceprions.DataStreamNotFound;
import com.masterproject.AUTOtech.agil.mapper.ArchitectureMapper;
import com.masterproject.AUTOtech.agil.mapper.DataStreamMapper;
import com.masterproject.AUTOtech.agil.repository.ArchitectureRepository;
import com.masterproject.AUTOtech.agil.repository.DataStreamRepository;
import com.masterproject.AUTOtech.agil.service.DataStreamService;

@Service
public class DataStreamServiceImpl implements DataStreamService{
	
	private DataStreamRepository dataStreamRepository;
	private ArchitectureRepository architectureRepository;

	public DataStreamServiceImpl(DataStreamRepository dataStreamRepository, ArchitectureRepository architectureRepository) {
		super();
		this.dataStreamRepository = dataStreamRepository;
		this.architectureRepository = architectureRepository;
	}

	@Override
	public DataStreamDto createDataStream(Long architecture_id, DataStreamDto dataStreamDto) {
		DataStream dataStream = DataStreamMapper.mapToDataStream(dataStreamDto);
		Architecture architecture = architectureRepository.findById(architecture_id).orElseThrow(() -> new ArchitectureNotFound("Architecture with associated ECU could not be found!"));
		dataStream.setArchitecture(architecture);
		DataStream savedDataStream = dataStreamRepository.save(dataStream);
		return DataStreamMapper.mapToDataStreamDto(savedDataStream);
	}

	@Override
	public List<DataStreamDto> getAllDataStreams(Long architecture_id) {
		List<DataStream> dataStreams = dataStreamRepository.findByArchitectureId(architecture_id);
		return dataStreams.stream().map(dataStream -> DataStreamMapper.mapToDataStreamDto(dataStream)).collect(Collectors.toList());
	}

	@Override
	public DataStreamDto updateDataStream(DataStreamDto dataStreamDto, Long id) {
		DataStream dataStream = dataStreamRepository
				.findById(id)
				.orElseThrow(()->new DataStreamNotFound("Data Stream could not be updated!"));
		
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
		
		DataStream updatedDataStream = dataStreamRepository.save(dataStream); 	
		return DataStreamMapper.mapToDataStreamDto(updatedDataStream);
	}

	@Override
	public void deleteDataStreamId(Long id) {
		DataStream dataStream = dataStreamRepository
				.findById(id)
				.orElseThrow(()->new DataStreamNotFound("Data Stream could not be deleted!"));
		dataStreamRepository.delete(dataStream);
		
	}

}
