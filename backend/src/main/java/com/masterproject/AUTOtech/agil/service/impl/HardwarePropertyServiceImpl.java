package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.HardwarePropertyDto;
import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.entity.Hardware;
import com.masterproject.AUTOtech.agil.entity.HardwareProperty;
import com.masterproject.AUTOtech.agil.entity.EcuSoftware;
import com.masterproject.AUTOtech.agil.exceprions.HardwarePropertyNotFound;
import com.masterproject.AUTOtech.agil.exceprions.HardwareNotFound;
import com.masterproject.AUTOtech.agil.exceprions.EcuSoftwareNotFound;
import com.masterproject.AUTOtech.agil.mapper.HardwarePropertyMapper;
import com.masterproject.AUTOtech.agil.mapper.EcuSoftwareMapper;
import com.masterproject.AUTOtech.agil.repository.HardwareRepository;
import com.masterproject.AUTOtech.agil.repository.HardwarePropertyRepository;
import com.masterproject.AUTOtech.agil.service.HardwarePropertyService;

@Service
public class HardwarePropertyServiceImpl implements HardwarePropertyService{
	
    private HardwarePropertyRepository hardwarePropertyRepository;
    private HardwareRepository hardwareRepository;

    public HardwarePropertyServiceImpl(HardwarePropertyRepository hardwarePropertyRepository, HardwareRepository hardwareRepository) {
        this.hardwarePropertyRepository = hardwarePropertyRepository;
        this.hardwareRepository = hardwareRepository;
    }

	@Override
	public HardwarePropertyDto createEcuHardware(Long ecu_id, HardwarePropertyDto hardwarePropertyDto) {
		HardwareProperty hardwareProperty = HardwarePropertyMapper.mapToHardwareProperty(hardwarePropertyDto);

        Hardware hardware = hardwareRepository.findById(ecu_id).orElseThrow(() -> new HardwareNotFound("ECU with associated hardware could not be found!"));
        
        hardwareProperty.setHardware(hardware);

        HardwareProperty newHardwareProperty = hardwarePropertyRepository.save(hardwareProperty);

        return HardwarePropertyMapper.mapToHardwarePropertyDto(newHardwareProperty);
	}

	@Override
	public List<HardwarePropertyDto> getAllEcuHardwareByEcuId(Long ecu_id) {
		List<HardwareProperty> allProperty = hardwarePropertyRepository.findByHardwareId(ecu_id);
		return allProperty.stream().map((hardware) -> HardwarePropertyMapper.mapToHardwarePropertyDto(hardware)).collect(Collectors.toList());
	}

	@Override
	public HardwarePropertyDto updateEcuHardware(HardwarePropertyDto hardwarePropertyDto, Long id) {
		HardwareProperty hardwareProperty = hardwarePropertyRepository
				.findById(id)
				.orElseThrow(()->new HardwarePropertyNotFound("EcuHardware could not be updated!"));
		
		hardwareProperty.setName(hardwarePropertyDto.getName());
		hardwareProperty.setValue(hardwarePropertyDto.getValue());
	
		
		HardwareProperty updatedhardwareProperty = hardwarePropertyRepository.save(hardwareProperty); 	
		return HardwarePropertyMapper.mapToHardwarePropertyDto(updatedhardwareProperty);
	}

	@Override
	public void deleteEcuHardwareId(Long id) {
		HardwareProperty hardwareProperty = hardwarePropertyRepository
				.findById(id)
				.orElseThrow(()->new HardwarePropertyNotFound("EcuSoftware could not be deleted!"));
		hardwarePropertyRepository.delete(hardwareProperty);
		
	}



}
