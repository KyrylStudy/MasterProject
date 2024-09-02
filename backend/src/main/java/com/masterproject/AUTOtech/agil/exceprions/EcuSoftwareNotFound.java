package com.masterproject.AUTOtech.agil.exceprions;

public class EcuSoftwareNotFound extends RuntimeException{
	
	private static final long serialVerisionUID = 1;

    public EcuSoftwareNotFound(String message) {
        super(message);
    }

}


