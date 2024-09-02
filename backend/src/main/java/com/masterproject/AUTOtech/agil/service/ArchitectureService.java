package com.masterproject.AUTOtech.agil.service;

import java.util.List;

import com.masterproject.AUTOtech.agil.dto.ArchitectureDto;

public interface ArchitectureService {
	
	ArchitectureDto createArchitecture(ArchitectureDto architectureDto);
	
	List<ArchitectureDto> getAllArchitecture();
	
	ArchitectureDto getArchitectureById(Long id);
	
	ArchitectureDto updateArchitecture(ArchitectureDto architectureDto, Long id);
	
	void deleteArchitectureId(Long id);

}
