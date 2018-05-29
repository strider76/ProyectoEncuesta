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

import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.dto.SurveyDTOPost;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.mapper.SurveyMapper;
import com.atsistemas.EncuestaProj.service.SurveyService;

@RestController
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	SurveyService surveyService;
	
	@Autowired
	SurveyMapper surveyMapper;
	
	@GetMapping("/{idSurvey}")
	@ResponseStatus(code=HttpStatus.OK)
	public SurveyDTOPost findOneByIdSurvey(@PathVariable Integer idSurvey) throws NotFoundException {
		return surveyMapper.surveyDaoToDto(surveyService.findById(idSurvey));
	}	
	
	@GetMapping("/course/{idCourse}")
	@ResponseStatus(code=HttpStatus.OK)
	public Set<SurveyDTOPost> findAllByCourse(@PathVariable Integer idCourse,
													@RequestParam(defaultValue="0",required=false,name="page") Integer pageNumber,
													@RequestParam(defaultValue="10",required=false,name="size") Integer pageSize) throws NotFoundException {
		 return surveyService.findAllByCourse(PageRequest.of(pageNumber, pageSize), idCourse);
	}
	
	@GetMapping("/tag/{idTag}")
	@ResponseStatus(code=HttpStatus.OK)
	public Set<SurveyDTOPost> findAllByTag(@PathVariable Integer idTag,
			@RequestParam(defaultValue="0",required=false,name="page") Integer pageNumber,
			@RequestParam(defaultValue="10",required=false,name="size") Integer pageSize) throws NotFoundException {
		return surveyService.findAllByTag(PageRequest.of(pageNumber, pageSize), idTag);
	}
	
	@GetMapping
	@ResponseStatus(code=HttpStatus.OK)
	public Set<SurveyDTOPost> findAll(@RequestParam(defaultValue="0",required=false,name="page") Integer pageNumber,
											@RequestParam(defaultValue="10",required=false,name="size") Integer pageSize) {
		return surveyMapper.surveyGetDaoToDto(surveyService.findAll(PageRequest.of(pageNumber, pageSize)));
	}
	
	@PostMapping("/course/")
	@ResponseStatus(code=HttpStatus.CREATED)
	public SurveyDTOPost create (@RequestBody SurveyDTO suerveyDTO) throws NotFoundException {
		return surveyMapper.surveyDaoToDto(surveyService.create(surveyMapper.surveyDtoToDao(suerveyDTO)));
	}
	
	@DeleteMapping("/{idSurvey}")
	@ResponseStatus(code=HttpStatus.OK)
	public void delete(@PathVariable Integer idSurvey) throws NotFoundException {
		surveyService.delete(idSurvey);
	}
	
	@PutMapping("/{idSurvey}")
	@ResponseStatus(code=HttpStatus.OK)
	public void update(@PathVariable Integer idSurvey, @RequestBody SurveyDTO surveyDTO) throws NotFoundException {
		surveyService.update(idSurvey, surveyDTO);
	}
	
	@PostMapping("/generate/{idSurvey}")
	@ResponseStatus(code=HttpStatus.CREATED)
	public void generateRamdomQuestionToSurvey(@PathVariable Integer idSurvey) throws SurveyNotFoundException {
		surveyService.generateRamdomQuestions(idSurvey);
	}
}
