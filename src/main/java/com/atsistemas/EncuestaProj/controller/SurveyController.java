package com.atsistemas.EncuestaProj.controller;

import java.util.List;

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

import com.atsistemas.EncuestaProj.dto.QuestionDTOPost;
import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.dto.SurveyDTOPost;
import com.atsistemas.EncuestaProj.dto.TagDTOPost;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyExcedQuestions;
import com.atsistemas.EncuestaProj.excepciones.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyQuestionRepeatedException;
import com.atsistemas.EncuestaProj.mapper.QuestionMapper;
import com.atsistemas.EncuestaProj.mapper.SurveyMapper;
import com.atsistemas.EncuestaProj.mapper.TagMapper;
import com.atsistemas.EncuestaProj.service.SurveyService;

@RestController
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	SurveyService surveyService;
	
	@Autowired
	SurveyMapper surveyMapper;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	TagMapper tagMapper;
	
	@GetMapping("/{idSurvey}")
	public SurveyDTOPost findOneByIdSurvey(@PathVariable Integer idSurvey) throws NotFoundException {
		return surveyMapper.surveyDaoToDto(surveyService.findById(idSurvey));
	}	

	@GetMapping("/{idSurvey}/question")
	public List<QuestionDTOPost> findQuestionBySurvey (@PathVariable Integer idSurvey,
			@RequestParam(defaultValue="0",required=false,name="page") Integer pageNumber,
			@RequestParam(defaultValue="10",required=false,name="size") Integer pageSize) throws SurveyNotFoundException  {
		return  questionMapper.QuestionGetDaoToDto(surveyService.findQuestionBySurvey(PageRequest.of(pageNumber, pageSize),idSurvey));
	}

	@GetMapping("/{idSurvey}/tag")
	public List<TagDTOPost> findAllByTag(@PathVariable Integer idSurvey,
			@RequestParam(defaultValue="0",required=false,name="page") Integer pageNumber,
			@RequestParam(defaultValue="10",required=false,name="size") Integer pageSize) throws NotFoundException {
		return tagMapper.tagsGetDaoToDto(surveyService.findTagBySurvey(PageRequest.of(pageNumber, pageSize), idSurvey));
	}
	
	@GetMapping
	public List<SurveyDTOPost> findAll(@RequestParam(defaultValue="0",required=false,name="page") Integer pageNumber,
								  	  @RequestParam(defaultValue="10",required=false,name="size") Integer pageSize) {
		return surveyMapper.surveyGetDaoToDto(surveyService.findAll(PageRequest.of(pageNumber, pageSize)));
	}
	
	@PostMapping("/course")
	@ResponseStatus(code=HttpStatus.CREATED)
	public SurveyDTOPost create (@RequestBody SurveyDTO suerveyDTO) throws NotFoundException {
		return surveyMapper.surveyDaoToDto(surveyService.create(surveyMapper.surveyDtoToDao(suerveyDTO)));
	}
	
	@DeleteMapping("/{idSurvey}")
	public void delete(@PathVariable Integer idSurvey) throws NotFoundException {
		surveyService.delete(idSurvey);
	}
	
	@PutMapping("/{idSurvey}")
	public void update(@PathVariable Integer idSurvey, @RequestBody SurveyDTO surveyDTO) throws NotFoundException {
		surveyService.update(idSurvey, surveyDTO);
	}
	
	@PostMapping("/{idSurvey}/generate")
	@ResponseStatus(code=HttpStatus.CREATED)
	public void generateRamdomQuestionToSurvey(@PathVariable Integer idSurvey) throws SurveyNotFoundException {
		surveyService.generateRamdomQuestions(idSurvey);
	}
	
	@PostMapping("/{idSurvey}/question/{idQuestion}")
	public void addQuestionToSurvey(@PathVariable Integer idSurvey, @PathVariable Integer idQuestion) throws NotFoundException, SurveyExcedQuestions, SurveyQuestionRepeatedException{
		surveyService.addQuestionToSurvey(idSurvey,idQuestion);
	}
	
	@PostMapping("/{idSurvey}/tag/{idTag}")
	public void addTagToSurvey(@PathVariable Integer idSurvey, @PathVariable Integer idTag) throws NotFoundException{
		surveyService.addTagToSurvey(idSurvey,idTag);
	}
	
	@DeleteMapping("/{idSurvey}/tag/{idTag}")
	public void removeTagToSurvey(@PathVariable Integer idSurvey, @PathVariable Integer idTag) throws NotFoundException{
		surveyService.removeTagToSurvey(idSurvey,idTag);
	}
}
