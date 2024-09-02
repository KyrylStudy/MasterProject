package com.masterproject.AUTOtech.agil.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.masterproject.AUTOtech.agil.dto.ArchitectureDto;
import com.masterproject.AUTOtech.agil.dto.HardwareDto;
import com.masterproject.AUTOtech.agil.entity.Architecture;
import com.masterproject.AUTOtech.agil.entity.Hardware;
import com.masterproject.AUTOtech.agil.exceprions.ArchitectureNotFound;
import com.masterproject.AUTOtech.agil.exceprions.HardwareNotFound;
import com.masterproject.AUTOtech.agil.mapper.ArchitectureMapper;
import com.masterproject.AUTOtech.agil.mapper.HardwareMapper;
import com.masterproject.AUTOtech.agil.repository.ArchitectureRepository;
import com.masterproject.AUTOtech.agil.service.ArchitectureService;

@Service
public class ArchitectureServiceImpl implements ArchitectureService{
	
	private ArchitectureRepository architectureRepository;

	public ArchitectureServiceImpl(ArchitectureRepository architectureRepository) {
		super();
		this.architectureRepository = architectureRepository;
	}
	
	@Override
	public ArchitectureDto createArchitecture(ArchitectureDto architectureDto) {
		Architecture architecture = ArchitectureMapper.mapToArchitecture(architectureDto);
		Architecture savedArchitecture = architectureRepository.save(architecture);
		return ArchitectureMapper.mapToArchitectureDto(savedArchitecture);
		
	}

	@Override
	public List<ArchitectureDto> getAllArchitecture() {
		List<Architecture> architectures = architectureRepository.findAll();
		return architectures.stream().map(architecture -> ArchitectureMapper.mapToArchitectureDto(architecture)).collect(Collectors.toList());
	}

	@Override
	public ArchitectureDto updateArchitecture(ArchitectureDto architectureDto, Long id) {
		Architecture architecture = architectureRepository
				.findById(id)
				.orElseThrow(()->new ArchitectureNotFound("Architecture could not be updated!"));
		
		architecture.setName(architectureDto.getName());
		architecture.setType(architectureDto.getType());
		architecture.setDescription(architectureDto.getDescription());
		
		Architecture updatedArchitecture = architectureRepository.save(architecture); 	
		return ArchitectureMapper.mapToArchitectureDto(updatedArchitecture);
	}

	@Override
	public void deleteArchitectureId(Long id) {
		Architecture architecture = architectureRepository
				.findById(id)
				.orElseThrow(()->new HardwareNotFound("Architecture could not be deleted!"));
		architectureRepository.delete(architecture);
		
	}

	@Override
	public ArchitectureDto getArchitectureById(Long id) {
		Architecture architecture = architectureRepository
				.findById(id)
				.orElseThrow(()->new ArchitectureNotFound("Architecture could not be found!"));
		return ArchitectureMapper.mapToArchitectureDto(architecture);
	}
	

}
