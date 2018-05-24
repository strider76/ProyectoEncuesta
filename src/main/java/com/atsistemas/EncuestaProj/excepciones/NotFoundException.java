package com.atsistemas.EncuestaProj.excepciones;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -8470572400722852877L;

	private final static String messageDefault = "Entidad no encontrada";
	
	public NotFoundException() {
		super(messageDefault);
	}
	
	public NotFoundException(String messageCustom) {
		super(messageCustom);
	}


}
