package com.atsistemas.EncuestaProj.service;

import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.atsistemas.EncuestaProj.dto.QuestionDTO;
import com.atsistemas.EncuestaProj.dto.QuestionDTOPost;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Tag;

public interface QuestionService extends AbstractService<Question, QuestionDTO, Integer>, InitializingBean {

	Set<QuestionDTOPost> findAllByTag(Integer idTag, Pageable page) throws TagNotFoundException, NotFoundException;
	Set<QuestionDTOPost> findAllByDificulty(Integer idDificulty, Pageable of) throws DificultyNotFoundException, NotFoundException;
	void assignSurvey(Integer idQuestion, Integer idSurvey) throws SurveyNotFoundException, NotFoundException;
	void removeSurvey(Integer idQuestion, Integer idSurvey) throws SurveyNotFoundException, NotFoundException;
	Set<Question> findAllbyTags(Set<Tag> tags);
	Set<QuestionDTOPost> findAllBySurvey(Integer idSurvey, PageRequest of) throws SurveyNotFoundException, NotFoundException;
	
	
}
