package com.atsistemas.EncuestaProj.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.atsistemas.EncuestaProj.dto.ErrorDTO;
import com.atsistemas.EncuestaProj.excepciones.AnswerNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.CourseNotfoundException;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.QuestionNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.UserNotFoundException;

@ControllerAdvice(basePackages={"com.atsistemas.EncuestaProj.controller"})
public class ExcepcionesController {

	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDTO> controlClaves(Exception ex,WebRequest request) {
		return new ResponseEntity<ErrorDTO>(new ErrorDTO("Error de Integridad referencial : " + ex.getMessage()), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDTO> existeUsuario(Exception ex) {
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CourseNotfoundException.class)
	public ResponseEntity<ErrorDTO> existeCurso(Exception ex){
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TagNotFoundException.class)
	public ResponseEntity<ErrorDTO> existeTag(Exception ex){
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
	}	
	
	@ExceptionHandler(DificultyNotFoundException.class)
	public ResponseEntity<ErrorDTO> existeDificult(Exception ex){
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(QuestionNotFoundException.class)
	public ResponseEntity<ErrorDTO> existeQuestion(Exception ex){
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SurveyNotFoundException.class)
	public ResponseEntity<ErrorDTO> existeSurvey(Exception ex){
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
	}	

	@ExceptionHandler(AnswerNotFoundException.class)
	public ResponseEntity<ErrorDTO> existeAnswer(Exception ex){
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
	}		
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ErrorDTO> exNumerico(Exception ex, WebRequest request) {
		return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
