package com.masterproject.AUTOtech.agil.service;

import java.util.List;

import com.masterproject.AUTOtech.agil.dto.EcuSoftwareDto;

public interface EcuSoftwareService {
	
	EcuSoftwareDto createEcuSoftwareDto(Long ecu_id, EcuSoftwareDto ecuSoftwareDto);
	
	EcuSoftwareDto getEcuSoftwareDtoById(Long id);
	
	List<EcuSoftwareDto> getAllEcuSoftwareByEcuId(Long ecu_id);
	
	EcuSoftwareDto updateEcuSoftware(EcuSoftwareDto ecuSoftwareDto, Long id);
	
	void deleteEcuSoftwareId(Long id);

}

