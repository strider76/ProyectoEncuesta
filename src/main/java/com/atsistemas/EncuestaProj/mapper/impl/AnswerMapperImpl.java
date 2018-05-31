package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.ArrayList;
import java.util.List;

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
	public List<Answer> AnswerGetsDtoToDao(List<AnswerDTOPost> answers) {
		List<Answer> answersList = new ArrayList<>();
		for (AnswerDTOPost answer : answers) 
			answersList.add(answerMapper.map(answer, Answer.class));
		return answersList;
	}

	@Override
	public List<AnswerDTOPost> AnswerGetsDaoToDto(List<Answer> answers) {
		List<AnswerDTOPost> answerList = new ArrayList<>();
		for (Answer curAnswer : answers) 
			answerList.add(answerDaoToDto(curAnswer));
		return answerList;
	}
}
