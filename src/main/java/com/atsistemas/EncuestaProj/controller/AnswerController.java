package com.atsistemas.EncuestaProj.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.AnswerDTOPost;
import com.atsistemas.EncuestaProj.dto.AnswerDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.mapper.AnswerMapper;
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
	
	@GetMapping("/{idAnswer}")
	public AnswerDTO findById(@PathVariable Integer idAnswer) throws NotFoundException {
		return answerMapper.answerDaoToDto(answerService.findById(idAnswer));
	}
	
	@DeleteMapping("/{idAnswer}")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public void delete(@PathVariable Integer idAnswer) throws NotFoundException {
		answerService.delete(idAnswer);
	}
	
	@PutMapping("/{idAnswer}")
	public void update(@PathVariable Integer idAnswer, @RequestBody AnswerDTOPost answerDTO) throws NotFoundException {
		answerService.update(idAnswer, answerDTO);
	}
	
	@GetMapping("/question/{idQuestion}")
	public Set<AnswerDTOPost> findAllByQuestion(@PathVariable Integer idQuestion) throws NotFoundException {
		return answerMapper.AnswerGetsDaoToDto(answerService.findAllByQuestion(idQuestion));
	}
}
