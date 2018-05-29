package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.EncuestaProj.dto.QuestionDTO;
import com.atsistemas.EncuestaProj.dto.QuestionDTOPost;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.mapper.QuestionMapper;
import com.atsistemas.EncuestaProj.model.Dificulty;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Tag;
import com.atsistemas.EncuestaProj.service.DificultyService;
import com.atsistemas.EncuestaProj.service.TagService;

@Component
public class QuestionMapperImpl implements QuestionMapper {

	
	@Autowired
	DozerBeanMapper questionMapper;
	
	@Autowired
	DificultyService dificultyService;
	
	@Autowired
	TagService tagService;
	
	
	@Override
	public Question questionDtoToDao(QuestionDTO questionDto) throws NotFoundException {
		
		Question question = questionMapper.map(questionDto, Question.class);
		Dificulty dificultySearch = dificultyService.findById(questionDto.getIdDificulty());
		Tag tagSearch = tagService.findById(questionDto.getIdTag());
		question.setDificulty(dificultySearch);
		question.setTag(tagSearch);
		return question;
		
	}

	@Override
	public QuestionDTOPost questionDaoToDto(Question question) {
		QuestionDTOPost questionDTOPost = questionMapper.map(question, QuestionDTOPost.class);
		questionDTOPost.setIdDificulty(question.getDificulty().getIdDificulty());
		questionDTOPost.setIdTag(question.getTag().getIdTag());
		return questionDTOPost;
	}

	@Override
	public Set<Question> questionGetDtoToDao(Set<QuestionDTO> questions) throws NotFoundException {
		Set<Question> questionList = new HashSet<>();
		for (QuestionDTO question : questions) 
			questionList.add(questionDtoToDao(question));
		return questionList;
	}

	@Override
	public Set<QuestionDTOPost> QuestionGetDaoToDto(Set<Question> questions) {
		Set<QuestionDTOPost> questionList = new HashSet<>();
		for (Question question : questions) 
			questionList.add(questionDaoToDto(question));
		return questionList;
	}

}
