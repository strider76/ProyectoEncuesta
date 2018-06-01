package com.atsistemas.EncuestaProj.excepciones;

public class SurveyFinishedException extends NotFoundException {

	private static final long serialVersionUID = -8043115602895825286L;

	public SurveyFinishedException(String message) {
		super(message);
	}
}
