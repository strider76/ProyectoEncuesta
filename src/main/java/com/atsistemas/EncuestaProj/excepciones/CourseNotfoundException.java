package com.atsistemas.EncuestaProj.excepciones;

public class CourseNotfoundException extends NotFoundException {

	private static final long serialVersionUID = 4501975892237398973L;

	public CourseNotfoundException(String message) {
		super(message);
	}
}
