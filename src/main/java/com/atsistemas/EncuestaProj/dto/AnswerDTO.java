package com.atsistemas.EncuestaProj.dto;


import lombok.Data;

@Data
public class AnswerDTO {

	private String respuesta;
	private Integer idPregunta;
	private Boolean esCorrecta;
}
