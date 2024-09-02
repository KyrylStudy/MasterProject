package com.masterproject.AUTOtech.agil.exceprions;

public class HardwareNotFound extends RuntimeException{
	
	private static final long serialVerisionUID = 1;

    public HardwareNotFound(String message) {
        super(message);
    }
}
