package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.dto.ServiceDto;
import com.masterproject.AUTOtech.agil.entity.Hardware;
import com.masterproject.AUTOtech.agil.entity.ServiceEntity;
import com.masterproject.AUTOtech.agil.exceprions.HardwareNotFound;
import com.masterproject.AUTOtech.agil.exceprions.ServiceNotFound;
import com.masterproject.AUTOtech.agil.mapper.HardwareMapper;
import com.masterproject.AUTOtech.agil.mapper.ServiceMapper;
import com.masterproject.AUTOtech.agil.repository.HardwareRepository;
import com.masterproject.AUTOtech.agil.repository.ServiceRepository;
import com.masterproject.AUTOtech.agil.service.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService{

	private ServiceRepository serviceRepository;
	private HardwareRepository hardwareRepository;
	
	
	@Autowired
	public ServiceServiceImpl(ServiceRepository serviceRepository, HardwareRepository hardwareRepository) {
		super();
		this.hardwareRepository = hardwareRepository;
		this.serviceRepository = serviceRepository;
	}


	@Override
	public ServiceDto createService(Long hardware_id, ServiceDto serviceDto) {
		ServiceEntity serviceEntity = ServiceMapper.mapToService(serviceDto);

        Hardware hardware = hardwareRepository.findById(hardware_id).orElseThrow(() -> new HardwareNotFound("ECU with associated Service could not be found!"));
        
        serviceEntity.setHardware(hardware);

        ServiceEntity newServiceEntity = serviceRepository.save(serviceEntity);

        return ServiceMapper.mapToServiceDto(newServiceEntity);
	}


	@Override
	public List<ServiceDto> getAllServicesByEcuId(Long hardware_id) {
			List<ServiceEntity> serviceEntities = serviceRepository.findByHardwareId(hardware_id);
			return serviceEntities.stream().map(service -> ServiceMapper.mapToServiceDto(service)).collect(Collectors.toList());
	}


	@Override
	public ServiceDto updateService(ServiceDto serviceDto, Long id) {
		ServiceEntity serviceEntity = serviceRepository
				.findById(id)
				.orElseThrow(()->new ServiceNotFound("Service could not be updated!"));
		
		serviceEntity.setName(serviceDto.getName());
		serviceEntity.setType(serviceDto.getType());
		serviceEntity.setDescription(serviceDto.getDescription());
		serviceEntity.setPositionX(serviceDto.getPositionX());
		serviceEntity.setPositionY(serviceDto.getPositionY());
		serviceEntity.setConnectedTo(serviceDto.getConnectedTo());
		//serviceEntity.setParent_ecu_id(serviceDto.getParent_ecu_id());
		
		ServiceEntity updatedServiceEntity = serviceRepository.save(serviceEntity); 	
		return ServiceMapper.mapToServiceDto(updatedServiceEntity);
	}


	@Override
	public void deleteServiceId(Long id) {
		ServiceEntity serviceEntity = serviceRepository
				.findById(id)
				.orElseThrow(()->new HardwareNotFound("Service could not be deleted!"));
		serviceRepository.delete(serviceEntity);
		
	}
}
