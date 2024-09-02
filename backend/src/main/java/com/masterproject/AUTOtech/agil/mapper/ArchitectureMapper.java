package com.masterproject.AUTOtech.agil.mapper;

import com.masterproject.AUTOtech.agil.dto.ArchitectureDto;
import com.masterproject.AUTOtech.agil.entity.Architecture;

public class ArchitectureMapper {
	
	public static Architecture mapToArchitecture(ArchitectureDto architectureDto) {
		Architecture architecture = new Architecture();

		architecture.setName(architectureDto.getName());
		architecture.setType(architectureDto.getType());
		architecture.setDescription(architectureDto.getDescription());

		
		return architecture;
	}
	
	public static ArchitectureDto mapToArchitectureDto(Architecture architecture) {
		ArchitectureDto architectureDto = new ArchitectureDto();
		
		architectureDto.setId(architecture.getId());
		architectureDto.setName(architecture.getName());
		architectureDto.setType(architecture.getType());
		architectureDto.setDescription(architecture.getDescription());
			
		return architectureDto;
	}

}
