package com.masterproject.AUTOtech.agil.service;

import java.util.List;

import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.entity.Hardware;

public interface HardwareService {
	
	HardwareDto createECU(Long architecture_id, HardwareDto ecudto);
	
	List<HardwareDto> getAllEcusByArchitectureId(Long architecture_id);
	
	HardwareDto getECUbyId(Long id);
	
	HardwareDto updateEcu(HardwareDto ecuDtro, Long id);
	
	void deleteEcuId(Long id);
	
	

}
