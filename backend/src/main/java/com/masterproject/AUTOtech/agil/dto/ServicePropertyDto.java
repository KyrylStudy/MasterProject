package com.masterproject.AUTOtech.agil.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicePropertyDto {
	
	private Long id;
	private String name;
    private String value;
    
}
