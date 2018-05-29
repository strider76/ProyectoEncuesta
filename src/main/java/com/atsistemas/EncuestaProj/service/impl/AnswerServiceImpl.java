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
		if (model.getQuestion().getAnswers().size()<4) {
			if (model.getEsCorrecta()) { answerToFalse(model.getQuestion()); }
			return answerDAO.save(model);
		} else {
			throw new AnswerNotFoundException("Error - Max numero de respuestas superadas");
		}
	}

	@Override
	public Answer findById(Integer id) throws NotFoundException {
		Optional<Answer> answerSearch = answerDAO.findById(id);
		if (answerSearch.isPresent())
			return answerSearch.get();
		else
			throw new AnswerNotFoundException("Answer no encontrada idAnswer('"+ id +"')");
	}

	@Override
	public Set<Answer> findAll(Pageable page) {
		return answerDAO.findAll(page).stream().collect(Collectors.toSet());
	}

	@Override
	public void update(Integer idAnswer, AnswerDTOPost answerDto) throws NotFoundException {
		Optional<Answer> answerSearch = answerDAO.findById(idAnswer);
		if (answerSearch.isPresent()) {
			Answer answer = answerSearch.get();
			answer.setEsCorrecta(answerDto.getEsCorrecta());
			answer.setRespuesta(answerDto.getRespuesta());
			if (answer.getEsCorrecta()) { answerToFalse(answer.getQuestion()); }
			answerDAO.save(answer);
		} else {
			throw new AnswerNotFoundException("Answer no encontrada idAnswer('"+ idAnswer +"')");
		}

	}

	@Override
	public void delete(Integer idAnswer) throws NotFoundException {
		Optional<Answer> answerSearch = answerDAO.findById(idAnswer);
		if (answerSearch.isPresent())
			answerDAO.delete(answerSearch.get());
		else
			throw new AnswerNotFoundException("Answer no encontrada idAnswer('"+ idAnswer +"')");
	}

	@Override
	public Boolean esCorrecta(Answer answer) {
		return answer.getEsCorrecta();
	}

	@Override
	public Set<Answer> findAllByQuestion(Integer idQuestion) throws NotFoundException {
		return answerDAO.findAllByQuestion(questionService.findById(idQuestion));
			
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
