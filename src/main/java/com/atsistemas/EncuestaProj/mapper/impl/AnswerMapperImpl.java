package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.EncuestaProj.dto.AnswerDTOPost;
import com.atsistemas.EncuestaProj.dto.AnswerDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.mapper.AnswerMapper;
import com.atsistemas.EncuestaProj.model.Answer;
import com.atsistemas.EncuestaProj.service.QuestionService;

@Component
public class AnswerMapperImpl implements AnswerMapper {

	@Autowired
	DozerBeanMapper answerMapper;
	
	@Autowired
	QuestionService questionService;

	@Override
	public AnswerDTOPost answerDaoToDto(Answer answer) {
		AnswerDTOPost answerRes = answerMapper.map(answer, AnswerDTOPost.class);
		answerRes.setIdPregunta(answer.getQuestion().getIdQuestion());
		return answerRes;
	}

	@Override
	public Answer answerDtoToDao(AnswerDTO answer) throws NotFoundException {
		Answer answerRes = answerMapper.map(answer, Answer.class);
		answerRes.setQuestion(questionService.findById(answer.getIdPregunta()));  
		return answerRes;
	}

	@Override
	public Set<Answer> AnswerGetsDtoToDao(Set<AnswerDTOPost> answers) {
		Set<Answer> answersList = new HashSet<>();
		for (AnswerDTOPost answer : answers) 
			answersList.add(answerMapper.map(answer, Answer.class));
		return answersList;
	}

	@Override
	public Set<AnswerDTOPost> AnswerGetsDaoToDto(Set<Answer> answers) {
		Set<AnswerDTOPost> answerList = new HashSet<>();
		for (Answer curAnswer : answers) 
			answerList.add(answerDaoToDto(curAnswer));
		return answerList;
	}
}
