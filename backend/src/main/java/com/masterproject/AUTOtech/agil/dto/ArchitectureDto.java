package com.masterproject.AUTOtech.agil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchitectureDto {
	
	private Long id;
	private String name;
	private String type;
	private String description;

}
