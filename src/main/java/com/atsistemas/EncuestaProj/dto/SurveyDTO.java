package com.atsistemas.EncuestaProj.dto;

import lombok.Data;

@Data
public class SurveyDTO {
	
	private String identificador;
	private Boolean esAleatorio;
	private Boolean esCerrado;
	private Integer maxPreguntas;
	private Integer idCourse;
	
}
