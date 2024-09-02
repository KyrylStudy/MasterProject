package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.ConnectionPropertyDto;
import com.masterproject.AUTOtech.agil.entity.Connection;
import com.masterproject.AUTOtech.agil.entity.ConnectionProperty;
import com.masterproject.AUTOtech.agil.exceprions.ConnectionNotFound;
import com.masterproject.AUTOtech.agil.exceprions.ConnectionPropertyNotFound;
import com.masterproject.AUTOtech.agil.mapper.ConnectionPropertyMapper;
import com.masterproject.AUTOtech.agil.repository.ConnectionPropertyRepository;
import com.masterproject.AUTOtech.agil.repository.ConnectionRepository;
import com.masterproject.AUTOtech.agil.service.ConnectionPropertyService;

@Service
public class ConnectionPropertyServiceImpl implements ConnectionPropertyService {

    private ConnectionRepository connectionRepository;
    private ConnectionPropertyRepository connectionPropertyRepository;

    public ConnectionPropertyServiceImpl(ConnectionPropertyRepository connectionPropertyRepository, ConnectionRepository connectionRepository) {
        this.connectionPropertyRepository = connectionPropertyRepository;
        this.connectionRepository = connectionRepository;
    }

    @Override
    public ConnectionPropertyDto createBusProperty(Long bus_id, ConnectionPropertyDto connectionPropertyDto) {
    	ConnectionProperty connectionProperty = ConnectionPropertyMapper.mapToConnectionProperty(connectionPropertyDto);

        Connection connection = connectionRepository.findById(bus_id).orElseThrow(() -> new ConnectionNotFound("Bus with associated Bus Property could not be found!"));
        
        connectionProperty.setConnection(connection);

        ConnectionProperty newConnectionProperty = connectionPropertyRepository.save(connectionProperty);

        return ConnectionPropertyMapper.mapToConnectionPropertyDto(newConnectionProperty);
    }

   /* @Override
    public EcuSoftwareDto getEcuSoftwareDtoById(Long id) {
        EcuSoftware ecuSoftware = ecuSoftwareRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("EcuSoftware does not exist"));
        return EcuSoftwareMapper.mapToEcuSoftwareDto(ecuSoftware);
    }*/

	@Override
	public List<ConnectionPropertyDto> getAllBusPropertyByBusId(Long bus_id) {
		List<ConnectionProperty> allConnectionProperty = connectionPropertyRepository.findByConnectionId(bus_id);
		return allConnectionProperty.stream().map((busProperty) -> ConnectionPropertyMapper.mapToConnectionPropertyDto(busProperty)).collect(Collectors.toList());

	}

	@Override
	public ConnectionPropertyDto updateBusProperty(ConnectionPropertyDto connectionPropertyDto, Long id) {
		ConnectionProperty connectionProperty = connectionPropertyRepository
				.findById(id)
				.orElseThrow(()->new ConnectionPropertyNotFound("Bus Property could not be updated!"));
		
		connectionProperty.setName(connectionPropertyDto.getName());
		connectionProperty.setValue(connectionPropertyDto.getValue());
	
		
		ConnectionProperty updatedConnectionProperty = connectionPropertyRepository.save(connectionProperty); 	
		return ConnectionPropertyMapper.mapToConnectionPropertyDto(updatedConnectionProperty);
	}

	@Override
	public void deleteBusPropertyId(Long id) {
		ConnectionProperty connectionProperty = connectionPropertyRepository
				.findById(id)
				.orElseThrow(()->new ConnectionPropertyNotFound("Bus Property could not be deleted!"));
		connectionPropertyRepository.delete(connectionProperty);
	}


}
