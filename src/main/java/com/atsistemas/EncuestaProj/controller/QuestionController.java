package com.atsistemas.EncuestaProj.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.AnswerDTOPost;
import com.atsistemas.EncuestaProj.dto.QuestionDTO;
import com.atsistemas.EncuestaProj.dto.QuestionDTOPost;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.QuestionNotFoundException;
import com.atsistemas.EncuestaProj.mapper.AnswerMapper;
import com.atsistemas.EncuestaProj.mapper.QuestionMapper;
import com.atsistemas.EncuestaProj.service.QuestionService;

@RestController
@RequestMapping(value="/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	AnswerMapper answerMapper;


	@GetMapping("/{idQuestion}")
	public QuestionDTOPost findById(@PathVariable Integer idQuestion) throws NotFoundException {
		return questionMapper.questionDaoToDto(questionService.findById(idQuestion));
	}
	
	@GetMapping
	public Set<QuestionDTOPost> findAll(@RequestParam(defaultValue="0",required=false,name="page") Integer pageNumber,
										@RequestParam(defaultValue="10", required=false,name="size") Integer pageSize) {
		return questionMapper.QuestionGetDaoToDto(questionService.findAll(PageRequest.of(pageNumber, pageSize)));
	}
	
	

	@GetMapping("/{idQuestion}/answer")
	public Set<AnswerDTOPost> findAnswerByQuestion(@PathVariable Integer idQuestion,
													 @RequestParam(defaultValue="0",required=false, name="page") Integer pageNumber,
													 @RequestParam(defaultValue="10",required=false,name="size") Integer pageSize) throws QuestionNotFoundException {
		return answerMapper.AnswerGetsDaoToDto(questionService.findAnswerByQuestion(PageRequest.of(pageNumber, pageSize),idQuestion));
	}

	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public QuestionDTOPost create(@RequestBody QuestionDTO questionDTO) throws NotFoundException {
		return questionMapper.questionDaoToDto(questionService.create(questionMapper.questionDtoToDao(questionDTO)));
	}
	
	@PostMapping("/{idQuestion}/survey/{idSurvey}")
	@ResponseStatus(code= HttpStatus.CREATED)
	public void assignQuestionToSuervey(@PathVariable Integer idQuestion, @PathVariable Integer idSurvey) throws NotFoundException {
		questionService.assignSurvey(idQuestion,idSurvey);
	}

	@DeleteMapping("/{idQuestion}/survey/{idSurvey}")
	public void removeQuestionToSuervey(@PathVariable Integer idQuestion, @PathVariable Integer idSurvey) throws NotFoundException {
		questionService.removeSurvey(idQuestion,idSurvey);
	}
		
	
	@DeleteMapping("/{idQuestion}")
	public void delete(@PathVariable Integer idQuestion) throws NotFoundException {
		questionService.delete(idQuestion);
	}
	
	@PutMapping("/{idQuestion}")
	@ResponseStatus(code=HttpStatus.OK)
	public void update(@PathVariable Integer idQuestion, @RequestBody QuestionDTO question) throws NotFoundException {
		questionService.update(idQuestion, question);
	}
	

	
}
