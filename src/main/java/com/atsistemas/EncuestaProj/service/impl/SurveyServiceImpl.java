package com.atsistemas.EncuestaProj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.SurveyDAO;
import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.dto.SurveyDTOPost;
import com.atsistemas.EncuestaProj.excepciones.CourseNotfoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
import com.atsistemas.EncuestaProj.mapper.SurveyMapper;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.Tag;
import com.atsistemas.EncuestaProj.service.CourseService;
import com.atsistemas.EncuestaProj.service.QuestionService;
import com.atsistemas.EncuestaProj.service.SurveyService;
import com.atsistemas.EncuestaProj.service.TagService;

@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	SurveyDAO surveyDAO;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	SurveyMapper surveyMapper;


	
	@Override
	public Survey create(Survey model) {
		return surveyDAO.save(model);
	}

	@Override
	public Set<Survey> findAll(Pageable pagina) {
		return surveyDAO.findAll(pagina).stream().collect(Collectors.toSet());
	}

	@Override
	public void update(Survey model, SurveyDTO dto) throws NotFoundException {
		Optional<Course> courseSearch = courseService.findById(dto.getIdCourse());
		if (courseSearch.isPresent()){
			model.setEsAleatorio(dto.getEsAleatorio());
			model.setIdentificador(dto.getIdentificador());
			model.setMaxPreguntas(dto.getMaxPreguntas());
			model.setCourse(courseSearch.get());
			surveyDAO.save(model);
		} else
			throw new CourseNotfoundException("Course no encontrado idCourse('"+ dto.getIdCourse() +"')");
		
	}

	@Override
	public void delete(Survey model) {
		surveyDAO.delete(model);
	}	
	
	@Override
	public Optional<Survey> findById(Integer id) {
		return surveyDAO.findById(id);
	}

	@Override
	public Set<SurveyDTOPost> findAllByCourse(Pageable page, int idCourse) throws CourseNotfoundException {
		Optional<Course> courseSearch = courseService.findById(idCourse);
		if (courseSearch.isPresent())
			return surveyMapper.surveyGetDaoToDto(surveyDAO.findAllByCourse(page, courseSearch.get()).stream().collect(Collectors.toSet()));
		else
			throw new CourseNotfoundException("Course no encontrado idCourse('"+ idCourse +"')");
	}	

	@Override
	public Set<SurveyDTOPost> findAllByTag(PageRequest page, Integer idTag) throws TagNotFoundException {
		Optional<Tag> tagSearch = tagService.findById(idTag);
		if (tagSearch.isPresent())
			return surveyMapper.surveyGetDaoToDto(surveyDAO.findAllByTags(page, tagSearch.get()).stream().collect(Collectors.toSet()));
		else
			throw new TagNotFoundException("Tag no encontrado idCourse('"+ idTag +"')");
	}
	
	@Override
	public void generateRamdomQuestions(Survey survey) {
			
		List<Question> questions = new ArrayList<Question>(questionService.findAllbyTags(survey.getTags()).stream().collect(Collectors.toSet()));
		while (questions.size()>0 && survey.getPreguntas().size()<survey.getMaxPreguntas())
			survey.getPreguntas().add(questions.remove(ThreadLocalRandom.current().nextInt(0, questions.size()-1)));
		if (survey.getPreguntas().size()==survey.getMaxPreguntas()) {survey.setEsCerrado(true);}
		surveyDAO.save(survey);
		
	}
	






}
