package com.atsistemas.EncuestaProj.service;

import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Pageable;

import com.atsistemas.EncuestaProj.dto.QuestionDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.QuestionNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.model.Answer;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Tag;

public interface QuestionService extends AbstractService<Question, QuestionDTO, Integer>, InitializingBean {

	void assignSurvey(Integer idQuestion, Integer idSurvey) throws SurveyNotFoundException, NotFoundException;
	void removeSurvey(Integer idQuestion, Integer idSurvey) throws SurveyNotFoundException, NotFoundException;
	Set<Answer> findAnswerByQuestion(Pageable page, Integer idQuestion) throws QuestionNotFoundException;
	Set<Question> findQuestionByTag(Set<Tag> tags);
	
	
}
