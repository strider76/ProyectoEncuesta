package com.atsistemas.EncuestaProj.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.AnswerDTOPost;
import com.atsistemas.EncuestaProj.dto.AnswerDTO;
import com.atsistemas.EncuestaProj.excepciones.AnswerNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.QuestionNotFoundException;
import com.atsistemas.EncuestaProj.mapper.AnswerMapper;
import com.atsistemas.EncuestaProj.model.Answer;
import com.atsistemas.EncuestaProj.service.AnswerService;

@RestController
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	@Autowired
	AnswerMapper answerMapper;
	
	@PostMapping("/question/{idQuestion}")
	@ResponseStatus(code=HttpStatus.CREATED)
	public AnswerDTO create(@RequestBody AnswerDTOPost answer) throws NotFoundException {
		return answerMapper.answerDaoToDto(answerService.create(answerMapper.answerDtoToDao(answer)));
	}
	
	@RequestMapping(value="/{idAnswer}", method= RequestMethod.GET)
	@ResponseStatus(code=HttpStatus.OK)
	public AnswerDTO findById(@PathVariable Integer idAnswer) throws AnswerNotFoundException {
		Optional<Answer> answerSearch = answerService.findById(idAnswer);
		if (answerSearch.isPresent())
			return answerMapper.answerDaoToDto(answerSearch.get());
		else
			throw new AnswerNotFoundException("No se ha encontrado la respuesta idAnswer('"+ idAnswer +"')");
	}
	
	@RequestMapping(value="/{idAnswer}", method= RequestMethod.DELETE)
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public void delete(@PathVariable Integer idAnswer) throws AnswerNotFoundException {
		Optional<Answer> answerSearch = answerService.findById(idAnswer);
		if (answerSearch.isPresent())
			answerService.delete(answerSearch.get());
		else
			throw new AnswerNotFoundException("No se ha encontrado la respuesta idAnswer('"+ idAnswer +"')");
	}
	
	@RequestMapping(value="/{idAnswer}", method=RequestMethod.PUT)
	@ResponseStatus(code=HttpStatus.OK)
	public void update(@PathVariable Integer idAnswer, @RequestBody AnswerDTOPost answerDTO) throws NotFoundException {
		Optional<Answer> answerSearch = answerService.findById(idAnswer);
		if (answerSearch.isPresent())
			answerService.update(answerSearch.get(), answerDTO);
		else
			throw new AnswerNotFoundException("No se ha encontrado la respuesta idAnswer('"+ idAnswer +"')");
	}
	
	@GetMapping("/question/{idQuestion}")
	@ResponseStatus(code=HttpStatus.OK)
	public Set<AnswerDTOPost> findAllByQuestion(@PathVariable Integer idQuestion) throws QuestionNotFoundException {
		return answerService.findAllByQuestion(idQuestion);
	}
}
