package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.ArchitectureDto;
import com.masterproject.AUTOtech.agil.dto.ConnectionDto;
import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.entity.Architecture;
import com.masterproject.AUTOtech.agil.entity.Connection;
import com.masterproject.AUTOtech.agil.entity.Hardware;
import com.masterproject.AUTOtech.agil.exceprions.ArchitectureNotFound;
import com.masterproject.AUTOtech.agil.exceprions.ConnectionNotFound;
import com.masterproject.AUTOtech.agil.exceprions.HardwareNotFound;
import com.masterproject.AUTOtech.agil.mapper.ArchitectureMapper;
import com.masterproject.AUTOtech.agil.mapper.ConnectionMapper;
import com.masterproject.AUTOtech.agil.mapper.HardwareMapper;
import com.masterproject.AUTOtech.agil.repository.ArchitectureRepository;
import com.masterproject.AUTOtech.agil.repository.ConnectionRepository;
import com.masterproject.AUTOtech.agil.service.ConnectionService;

@Service
public class ConnectionServiceImpl implements ConnectionService{
	
	private ConnectionRepository connectionRepository;
	private ArchitectureRepository architectureRepository;

	public ConnectionServiceImpl(ConnectionRepository connectionRepository, ArchitectureRepository architectureRepository) {
		super();
		this.connectionRepository = connectionRepository;
		this.architectureRepository = architectureRepository;
	}

	@Override
	public ConnectionDto createBus(Long architecture_id, ConnectionDto connectionDto) {
		Connection connection = ConnectionMapper.mapToConnection(connectionDto);
		Architecture architecture = architectureRepository.findById(architecture_id).orElseThrow(() -> new ArchitectureNotFound("Architecture with associated ECU could not be found!"));
		connection.setArchitecture(architecture);
		Connection savedConnection = connectionRepository.save(connection);
		return ConnectionMapper.mapToConnectionDto(savedConnection);
	}

	@Override
	public List<ConnectionDto> getAllBuses(Long architecture_id) {
		List<Connection> buses = connectionRepository.findByArchitectureId(architecture_id);
		return buses.stream().map(bus -> ConnectionMapper.mapToConnectionDto(bus)).collect(Collectors.toList());
	}

	@Override
	public ConnectionDto updateBus(ConnectionDto connectionDto, Long id) {
		Connection connection = connectionRepository
				.findById(id)
				.orElseThrow(()->new ConnectionNotFound("Architecture could not be updated!"));
		
		connection.setName(connectionDto.getName());
		connection.setType(connectionDto.getType());
		connection.setDescription(connectionDto.getDescription());
		connection.setPositionFromX(connectionDto.getPositionFromX());
		connection.setPositionFromY(connectionDto.getPositionFromY());
		connection.setPositionToX(connectionDto.getPositionToX());
		connection.setPositionToY(connectionDto.getPositionToY());
		connection.setConnectedFrom(connectionDto.getConnectedFrom());
		connection.setConnectedTo(connectionDto.getConnectedTo());
		connection.setTwoWayConnection(connectionDto.getTwoWayConnection());
		
		Connection updatedConnection = connectionRepository.save(connection); 	
		return ConnectionMapper.mapToConnectionDto(updatedConnection);
	}

	@Override
	public void deleteBusId(Long id) {
		Connection connection = connectionRepository
				.findById(id)
				.orElseThrow(()->new ConnectionNotFound("Bus could not be deleted!"));
		connectionRepository.delete(connection);
		
	}

}
