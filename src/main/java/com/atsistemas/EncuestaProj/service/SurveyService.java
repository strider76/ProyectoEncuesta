package com.atsistemas.EncuestaProj.service;

import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.dto.SurveyDTOPost;
import com.atsistemas.EncuestaProj.excepciones.CourseNotfoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
import com.atsistemas.EncuestaProj.model.Survey;

public interface SurveyService extends AbstractService<Survey, SurveyDTO, Integer> {

	Set<SurveyDTOPost> findAllByCourse(Pageable page, int idCourse) throws CourseNotfoundException;
	Set<SurveyDTOPost> findAllByTag(PageRequest page, Integer idTag) throws TagNotFoundException;
	void generateRamdomQuestions(Survey survey);
	
}