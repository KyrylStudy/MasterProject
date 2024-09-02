package com.masterproject.AUTOtech.agil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataStreamDto {
	
	private Long id;
	private String name;
	private String type;
	private String description;
	private String positionFromX;
	private String positionFromY;
	private String positionToX;
	private String positionToY;
	private String connectedFrom;
	private String connectedTo;
	private Boolean twoWayConnection;

}

