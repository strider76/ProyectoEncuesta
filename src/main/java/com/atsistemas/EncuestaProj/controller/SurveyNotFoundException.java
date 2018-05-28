package com.atsistemas.EncuestaProj.controller;

import com.atsistemas.EncuestaProj.excepciones.NotFoundException;

public class SurveyNotFoundException extends NotFoundException {

	private static final long serialVersionUID = -1569520967879166889L;
	
	public SurveyNotFoundException(String message) {
		super(message);
	}

}
