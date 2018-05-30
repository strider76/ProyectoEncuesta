package com.atsistemas.EncuestaProj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultSurveyDTO {

	private String question;
	private String answer;
	private String resultado;
	
}
