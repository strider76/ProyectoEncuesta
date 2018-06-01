package com.atsistemas.EncuestaProj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultCourseDTO {

	private Integer idSurvey;
	private String surveyName;
	private double nota;
	
}
