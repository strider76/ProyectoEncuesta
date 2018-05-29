package com.atsistemas.EncuestaProj.mapper;

import java.util.Set;

import com.atsistemas.EncuestaProj.dto.QuestionDTO;
import com.atsistemas.EncuestaProj.dto.QuestionDTOPost;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
import com.atsistemas.EncuestaProj.model.Question;

public interface QuestionMapper {

	Question questionDtoToDao(QuestionDTO questionDto) throws DificultyNotFoundException, TagNotFoundException, NotFoundException;
	QuestionDTOPost questionDaoToDto(Question question);
	Set<Question> questionGetDtoToDao(Set<QuestionDTO> questions) throws DificultyNotFoundException, TagNotFoundException, NotFoundException;
	Set<QuestionDTOPost> QuestionGetDaoToDto(Set<Question> questions);
	
}
