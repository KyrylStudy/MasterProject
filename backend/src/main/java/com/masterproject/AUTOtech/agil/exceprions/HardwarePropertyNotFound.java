package com.masterproject.AUTOtech.agil.exceprions;

public class HardwarePropertyNotFound extends RuntimeException{
	
	private static final long serialVerisionUID = 1;

    public HardwarePropertyNotFound(String message) {
        super(message);
    }
}

