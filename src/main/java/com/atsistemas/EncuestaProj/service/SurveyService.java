package com.atsistemas.EncuestaProj.service;

import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyExcedQuestions;
import com.atsistemas.EncuestaProj.excepciones.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyQuestionRepeatedException;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.Tag;

public interface SurveyService extends AbstractService<Survey, SurveyDTO, Integer> {

	
	void generateRamdomQuestions(Integer idSurvey) throws SurveyNotFoundException;
	Set<Question> findQuestionBySurvey(Pageable page, Integer idSurvey) throws SurveyNotFoundException;
	Set<Tag> findTagBySurvey(Pageable page, Integer idSurvey) throws SurveyNotFoundException;
	void addTagToSurvey(Integer idSurvey, Integer idTag) throws NotFoundException;
	void removeTagToSurvey(Integer idSurvey, Integer idTag) throws NotFoundException;
	void addQuestionToSurvey(Integer idSurvey, Integer idQuestion) throws NotFoundException, SurveyExcedQuestions, SurveyQuestionRepeatedException;
	
}
