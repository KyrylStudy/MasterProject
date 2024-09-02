package com.masterproject.AUTOtech.agil.exceprions;

public class ServicePropertyNotFound extends RuntimeException{
	
	private static final long serialVerisionUID = 1;

    public ServicePropertyNotFound(String message) {
        super(message);
    }
}