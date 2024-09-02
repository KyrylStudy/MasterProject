package com.masterproject.AUTOtech.agil.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareDto {
	
	private Long id;
	private String label;
	private String type;
	private String description;
	private int positionX;
	private int positionY;
	private String connectedTo;
	//private List<EcuSoftware> software;

}
