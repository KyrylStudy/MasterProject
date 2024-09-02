package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.dto.ServicePropertyDto;
import com.masterproject.AUTOtech.agil.entity.Hardware;
import com.masterproject.AUTOtech.agil.entity.EcuSoftware;
import com.masterproject.AUTOtech.agil.entity.ServiceEntity;
import com.masterproject.AUTOtech.agil.entity.ServiceProperty;
import com.masterproject.AUTOtech.agil.exceprions.HardwareNotFound;
import com.masterproject.AUTOtech.agil.exceprions.EcuSoftwareNotFound;
import com.masterproject.AUTOtech.agil.exceprions.ServiceNotFound;
import com.masterproject.AUTOtech.agil.exceprions.ServicePropertyNotFound;
import com.masterproject.AUTOtech.agil.mapper.EcuSoftwareMapper;
import com.masterproject.AUTOtech.agil.mapper.ServicePropertyMapper;
import com.masterproject.AUTOtech.agil.repository.HardwareRepository;
import com.masterproject.AUTOtech.agil.repository.EcuSoftwareRepository;
import com.masterproject.AUTOtech.agil.repository.ServicePropertyRepository;
import com.masterproject.AUTOtech.agil.repository.ServiceRepository;
import com.masterproject.AUTOtech.agil.service.ServicePropertyService;

@Service
public class ServicePropertyServiceImpl implements ServicePropertyService{
	
    private ServicePropertyRepository servicePropertyRepository;
    private ServiceRepository serviceRepository;

    public ServicePropertyServiceImpl(ServicePropertyRepository servicePropertyRepository, ServiceRepository serviceRepository) {
        this.servicePropertyRepository = servicePropertyRepository;
        this.serviceRepository = serviceRepository;
    }

	@Override
	public ServicePropertyDto createServiceProperty(Long service_id, ServicePropertyDto servicePropertyDto) {
		ServiceProperty serviceProperty = ServicePropertyMapper.mapToServiceProperty(servicePropertyDto);

		ServiceEntity serviceEntity = serviceRepository.findById(service_id).orElseThrow(() -> new ServiceNotFound("Service with associated Service Property could not be found!"));
        
		serviceProperty.setServiceEntity(serviceEntity);

		ServiceProperty newServiceProperty = servicePropertyRepository.save(serviceProperty);

        return ServicePropertyMapper.mapToServicePropertyDto(newServiceProperty);
	}

	@Override
	public List<ServicePropertyDto> getAllServicePropertiesByServiceId(Long service_id) {
		List<ServiceProperty> allServiceProperties = servicePropertyRepository.findByServiceEntityId(service_id);
		return allServiceProperties.stream().map((serviceProperty) -> ServicePropertyMapper.mapToServicePropertyDto(serviceProperty)).collect(Collectors.toList());

	}

	@Override
	public ServicePropertyDto updateServiceProperty(ServicePropertyDto servicePropertyDto, Long id) {
		ServiceProperty serviceProperty = servicePropertyRepository
				.findById(id)
				.orElseThrow(()->new ServicePropertyNotFound("ServiceProperty could not be updated!"));
		
		serviceProperty.setName(servicePropertyDto.getName());
		serviceProperty.setValue(servicePropertyDto.getValue());
	
		
		ServiceProperty updatedServiceProperty = servicePropertyRepository.save(serviceProperty); 	
		return ServicePropertyMapper.mapToServicePropertyDto(updatedServiceProperty);
	}

	@Override
	public void deleteServiceProperty(Long id) {
		ServiceProperty serviceProperty = servicePropertyRepository
				.findById(id)
				.orElseThrow(()->new ServicePropertyNotFound("ServiceProperty could not be deleted!"));
		servicePropertyRepository.delete(serviceProperty);
		
	}



}
