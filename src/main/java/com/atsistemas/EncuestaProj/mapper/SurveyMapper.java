package com.atsistemas.EncuestaProj.mapper;

import java.util.Set;

import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.dto.SurveyDTOPost;
import com.atsistemas.EncuestaProj.excepciones.CourseNotfoundException;
import com.atsistemas.EncuestaProj.model.Survey;

public interface SurveyMapper {

	Survey surveyDtoToDao(SurveyDTO survey) throws CourseNotfoundException;
	SurveyDTOPost surveyDaoToDto(Survey survey);
	Set<SurveyDTOPost> surveyGetDaoToDto(Set<Survey> surveys);
	Set<Survey> surveyGetDtoToDao(Set<SurveyDTO> surveys) throws CourseNotfoundException;
	
}
