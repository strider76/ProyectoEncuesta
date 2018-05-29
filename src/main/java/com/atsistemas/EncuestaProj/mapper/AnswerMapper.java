package com.atsistemas.EncuestaProj.mapper;

import java.util.Set;

import com.atsistemas.EncuestaProj.dto.AnswerDTOPost;
import com.atsistemas.EncuestaProj.dto.AnswerDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.QuestionNotFoundException;
import com.atsistemas.EncuestaProj.model.Answer;

public interface AnswerMapper {

	AnswerDTOPost answerDaoToDto(Answer answer);
	Answer answerDtoToDao(AnswerDTO answer) throws QuestionNotFoundException, NotFoundException;
	Set<Answer> AnswerGetsDtoToDao(Set<AnswerDTOPost> answers);
	Set<AnswerDTOPost> AnswerGetsDaoToDto(Set<Answer> answers);
	
}
