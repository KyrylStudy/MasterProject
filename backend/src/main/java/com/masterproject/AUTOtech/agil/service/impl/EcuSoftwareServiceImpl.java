package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;
import com.masterproject.AUTOtech.agil.entity.Hardware;
import com.masterproject.AUTOtech.agil.entity.EcuSoftware;
import com.masterproject.AUTOtech.agil.exceprions.HardwareNotFound;
import com.masterproject.AUTOtech.agil.exceprions.EcuSoftwareNotFound;
import com.masterproject.AUTOtech.agil.mapper.EcuSoftwareMapper;
import com.masterproject.AUTOtech.agil.repository.HardwareRepository;
import com.masterproject.AUTOtech.agil.repository.EcuSoftwareRepository;
import com.masterproject.AUTOtech.agil.service.EcuSoftwareService;

@Service
public class EcuSoftwareServiceImpl implements EcuSoftwareService {

    private EcuSoftwareRepository ecuSoftwareRepository;
    private HardwareRepository hardwareRepository;

    public EcuSoftwareServiceImpl(EcuSoftwareRepository ecuSoftwareRepository, HardwareRepository hardwareRepository) {
        this.ecuSoftwareRepository = ecuSoftwareRepository;
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public EcuSoftwareDto createEcuSoftwareDto(Long ecu_id, EcuSoftwareDto ecuSoftwareDto) {
        EcuSoftware ecuSoftware = EcuSoftwareMapper.mapToEcuSoftware(ecuSoftwareDto);

        Hardware hardware = hardwareRepository.findById(ecu_id).orElseThrow(() -> new HardwareNotFound("ECU with associated software could not be found!"));
        
        ecuSoftware.setHardware(hardware);

        EcuSoftware newEcuSoftware = ecuSoftwareRepository.save(ecuSoftware);

        return EcuSoftwareMapper.mapToEcuSoftwareDto(newEcuSoftware);
    }

    @Override
    public EcuSoftwareDto getEcuSoftwareDtoById(Long id) {
        EcuSoftware ecuSoftware = ecuSoftwareRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("EcuSoftware does not exist"));
        return EcuSoftwareMapper.mapToEcuSoftwareDto(ecuSoftware);
    }

	@Override
	public List<EcuSoftwareDto> getAllEcuSoftwareByEcuId(Long ecu_id) {
		List<EcuSoftware> allSoftware = ecuSoftwareRepository.findByHardwareId(ecu_id);
		return allSoftware.stream().map((software) -> EcuSoftwareMapper.mapToEcuSoftwareDto(software)).collect(Collectors.toList());

	}

	@Override
	public EcuSoftwareDto updateEcuSoftware(EcuSoftwareDto ecuSoftwareDto, Long id) {
		EcuSoftware ecuSoftware = ecuSoftwareRepository
				.findById(id)
				.orElseThrow(()->new EcuSoftwareNotFound("EcuSoftware could not be updated!"));
		
		ecuSoftware.setName(ecuSoftwareDto.getName());
		ecuSoftware.setValue(ecuSoftwareDto.getValue());
	
		
		EcuSoftware updatedEcuSoftware = ecuSoftwareRepository.save(ecuSoftware); 	
		return EcuSoftwareMapper.mapToEcuSoftwareDto(updatedEcuSoftware);
	}

	@Override
	public void deleteEcuSoftwareId(Long id) {
		EcuSoftware ecuSoftware = ecuSoftwareRepository
				.findById(id)
				.orElseThrow(()->new EcuSoftwareNotFound("EcuSoftware could not be deleted!"));
		ecuSoftwareRepository.delete(ecuSoftware);
	}
    

}
