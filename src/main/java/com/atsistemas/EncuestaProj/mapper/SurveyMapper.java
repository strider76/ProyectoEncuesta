package com.atsistemas.EncuestaProj.mapper;

import java.util.List;

import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.dto.SurveyDTOPost;
import com.atsistemas.EncuestaProj.excepciones.CourseNotfoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.model.Survey;

public interface SurveyMapper {

	Survey surveyDtoToDao(SurveyDTO survey) throws CourseNotfoundException, NotFoundException;
	SurveyDTOPost surveyDaoToDto(Survey survey);
	List<SurveyDTOPost> surveyGetDaoToDto(List<Survey> surveys);
	List<Survey> surveyGetDtoToDao(List<SurveyDTO> surveys) throws CourseNotfoundException, NotFoundException;
	
}
