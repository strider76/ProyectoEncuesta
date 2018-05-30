package com.atsistemas.EncuestaProj.excepciones;

public class SurveyQuestionRepeatedException extends Exception {

	private static final long serialVersionUID = -3194663193358663535L;
	
	public SurveyQuestionRepeatedException(String message){
		super(message);
	}

}
