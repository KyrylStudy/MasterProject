package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.Optional;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.entity.Architecture;
import com.masterproject.AUTOtech.agil.entity.Hardware;
import com.masterproject.AUTOtech.agil.entity.EcuSoftware;
import com.masterproject.AUTOtech.agil.exceprions.ArchitectureNotFound;
import com.masterproject.AUTOtech.agil.exceprions.HardwareNotFound;
import com.masterproject.AUTOtech.agil.mapper.HardwareMapper;
import com.masterproject.AUTOtech.agil.mapper.EcuSoftwareMapper;
import com.masterproject.AUTOtech.agil.repository.ArchitectureRepository;
import com.masterproject.AUTOtech.agil.repository.HardwareRepository;
import com.masterproject.AUTOtech.agil.service.HardwareService;

@Service
public class HardwareServiceImpl implements HardwareService{
	
	private HardwareRepository hardwareRepository;
	private ArchitectureRepository architectureRepository;
	
	
	@Autowired
	public HardwareServiceImpl(HardwareRepository hardwareRepository, ArchitectureRepository architectureRepository) {
		super();
		this.hardwareRepository = hardwareRepository;
		this.architectureRepository = architectureRepository;
	}


	@Override
	public HardwareDto createECU(Long architecture_id, HardwareDto ecudto) {
		Hardware hardware = HardwareMapper.mapToHardware(ecudto);

        Architecture architecture = architectureRepository.findById(architecture_id).orElseThrow(() -> new ArchitectureNotFound("Architecture with associated ECU could not be found!"));
        
        hardware.setArchitecture(architecture);

        Hardware newHardware = hardwareRepository.save(hardware);

        return HardwareMapper.mapToHardwareDto(newHardware);
		
	}


	@Override
	public HardwareDto getECUbyId(Long id) {
		Hardware hardware = hardwareRepository
				.findById(id)
				.orElseThrow(()->new HardwareNotFound("ECU could not be found!"));
		return HardwareMapper.mapToHardwareDto(hardware);
	}



	@Override
	public List<HardwareDto> getAllEcusByArchitectureId(Long architecture_id) {
		//ECU ecu228 = ecuRepository.findById((long) 654654654).orElseThrow(()-> new EcuNotFound("ECU can not be found by ID!"));
		List<Hardware> ecus = hardwareRepository.findByArchitectureId(architecture_id);
		return ecus.stream().map(ecu -> HardwareMapper.mapToHardwareDto(ecu)).collect(Collectors.toList());

	}



	@Override
	public HardwareDto updateEcu(HardwareDto ecuDto, Long id) {
		Hardware hardware = hardwareRepository
				.findById(id)
				.orElseThrow(()->new HardwareNotFound("ECU could not be updated!"));
		
		hardware.setLabel(ecuDto.getLabel());
		hardware.setType(ecuDto.getType());
		hardware.setDescription(ecuDto.getDescription());
		hardware.setPositionX(ecuDto.getPositionX());
		hardware.setPositionY(ecuDto.getPositionY());
		hardware.setConnectedTo(ecuDto.getConnectedTo());
		
		Hardware updatedHardware = hardwareRepository.save(hardware); 	
		return HardwareMapper.mapToHardwareDto(updatedHardware);
	}



	@Override
	public void deleteEcuId(Long id) {
		Hardware hardware = hardwareRepository
				.findById(id)
				.orElseThrow(()->new HardwareNotFound("ECU could not be deleted!"));
		hardwareRepository.delete(hardware);
	}




	

}
