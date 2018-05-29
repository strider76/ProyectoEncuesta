package com.atsistemas.EncuestaProj.service;

import java.util.Set;

import com.atsistemas.EncuestaProj.dto.AnswerDTOPost;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.QuestionNotFoundException;
import com.atsistemas.EncuestaProj.model.Answer;

public interface AnswerService extends AbstractService<Answer, AnswerDTOPost	, Integer> {

	Boolean esCorrecta(Answer answer);
	Set<Answer> findAllByQuestion(Integer idQuestion) throws QuestionNotFoundException, NotFoundException;
}
