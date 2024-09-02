package com.masterproject.AUTOtech.agil.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
	
	private Long id;
	private String name;
	private String type;
	private String description;
	private int positionX;
	private int positionY;
	private String connectedTo;
	//private Long parent_ecu_id;

}
