package com.masterproject.AUTOtech.agil.mapper;

import com.masterproject.AUTOtech.agil.dto.ConnectionDto;
import com.masterproject.AUTOtech.agil.entity.Connection;

public class ConnectionMapper {
	
	public static Connection mapToConnection(ConnectionDto connectionDto) {
		Connection connection = new Connection();

		connection.setName(connectionDto.getName());
		connection.setType(connectionDto.getType());
		connection.setDescription(connectionDto.getDescription());
		connection.setPositionFromX(connectionDto.getPositionFromX());
		connection.setPositionFromY(connectionDto.getPositionFromY());
		connection.setPositionToX(connectionDto.getPositionToX());
		connection.setPositionToY(connectionDto.getPositionToY());
		connection.setConnectedFrom(connectionDto.getConnectedFrom());
		connection.setConnectedTo(connectionDto.getConnectedTo());
		connection.setTwoWayConnection(connectionDto.getTwoWayConnection());

		
		return connection;
	}
	
	public static ConnectionDto mapToConnectionDto(Connection connection) {
		ConnectionDto connectionDto = new ConnectionDto();
		
		connectionDto.setId(connection.getId());
		connectionDto.setName(connection.getName());
		connectionDto.setType(connection.getType());
		connectionDto.setDescription(connection.getDescription());
		connectionDto.setPositionFromX(connection.getPositionFromX());
		connectionDto.setPositionFromY(connection.getPositionFromY());
		connectionDto.setPositionToX(connection.getPositionToX());
		connectionDto.setPositionToY(connection.getPositionToY());
		connectionDto.setConnectedFrom(connection.getConnectedFrom());
		connectionDto.setConnectedTo(connection.getConnectedTo());
		connectionDto.setTwoWayConnection(connection.getTwoWayConnection());

			
		return connectionDto;
	}

}

