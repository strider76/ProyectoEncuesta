package com.atsistemas.EncuestaProj.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.AnswerDAO;
import com.atsistemas.EncuestaProj.dto.AnswerDTOPost;
import com.atsistemas.EncuestaProj.excepciones.AnswerNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.QuestionNotFoundException;
import com.atsistemas.EncuestaProj.mapper.AnswerMapper;
import com.atsistemas.EncuestaProj.model.Answer;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.service.AnswerService;
import com.atsistemas.EncuestaProj.service.QuestionService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerMapper answerMapper;
	
	@Override
	public Answer create(Answer model) throws NotFoundException {
		int size = questionService.findById(model.getQuestion().getIdQuestion()).get().getAnswers().size();
		if (size<4) {
			if (model.getEsCorrecta()) { answerToFalse(model.getQuestion()); }
			return answerDAO.save(model);
		} else {
			throw new AnswerNotFoundException("Error - Max numero de respuestas superadas");
		}
	}

	@Override
	public Optional<Answer> findById(Integer id) {
		return answerDAO.findById(id);
	}

	@Override
	public Set<Answer> findAll(Pageable page) {
		return answerDAO.findAll(page).stream().collect(Collectors.toSet());
	}

	@Override
	public void update(Answer model, AnswerDTOPost answerDto) {
		if (model.getEsCorrecta()) { answerToFalse(model.getQuestion()); }
		model.setRespuesta(answerDto.getRespuesta());
		model.setEsCorrecta(answerDto.getEsCorrecta());
		answerDAO.save(model);

	}

	@Override
	public void delete(Answer model) {
		answerDAO.delete(model);
	}

	@Override
	public Boolean esCorrecta(Answer answer) {
		return answer.getEsCorrecta();
	}

	@Override
	public Set<AnswerDTOPost> findAllByQuestion(Integer idQuestion) throws QuestionNotFoundException {
		Optional<Question> questionSearch = questionService.findById(idQuestion);
		if (questionSearch.isPresent())
			return answerMapper.AnswerGetsDaoToDto(answerDAO.findAllByQuestion(questionSearch.get()));
		else
			throw new QuestionNotFoundException("Question no encontrada idQuestion('"+ idQuestion +"')");
				
	}
	
	private void answerToFalse (Question question) {
		Set<Answer> answersQuestions = answerDAO.findAllByQuestion(question);
		for (Answer answer : answersQuestions) 
			if (answer.getEsCorrecta()){
				answer.setEsCorrecta(false);
				answerDAO.save(answer);
			}
	}

}
