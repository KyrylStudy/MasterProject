package com.masterproject.AUTOtech.agil.service;


import java.util.List;

import com.masterproject.AUTOtech.agil.dto.HardwarePropertyDto;


public interface HardwarePropertyService {
	
	HardwarePropertyDto createEcuHardware(Long ecu_id, HardwarePropertyDto hardwarePropertyDto);
	
	List<HardwarePropertyDto> getAllEcuHardwareByEcuId(Long ecu_id);
	
	HardwarePropertyDto updateEcuHardware(HardwarePropertyDto hardwarePropertyDto, Long id);
	
	void deleteEcuHardwareId(Long id);

}
