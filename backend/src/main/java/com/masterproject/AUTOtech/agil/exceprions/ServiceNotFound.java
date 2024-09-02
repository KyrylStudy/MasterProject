package com.masterproject.AUTOtech.agil.exceprions;

public class ServiceNotFound extends RuntimeException{
	
	private static final long serialVerisionUID = 1;

    public ServiceNotFound(String message) {
        super(message);
    }
}
