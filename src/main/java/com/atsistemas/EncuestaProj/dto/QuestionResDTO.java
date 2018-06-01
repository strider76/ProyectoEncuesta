package com.atsistemas.EncuestaProj.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResDTO {

	private Integer idQuestion;
	private String pregunta;
	private List<AnswerResDTO> respuestas = new ArrayList<>();
	
}
