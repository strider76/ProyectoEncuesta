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
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyNotFoundException;
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
	public void update(Integer idSurvey, SurveyDTO dto) throws NotFoundException {
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		if (surveySearch.isPresent()){
			Survey survey = surveySearch.get();
			survey.setEsAleatorio(dto.getEsAleatorio());
			survey.setIdentificador(dto.getIdentificador());
			survey.setMaxPreguntas(dto.getMaxPreguntas());
			survey.setCourse(courseService.findById(dto.getIdCourse()));
			surveyDAO.save(survey);
		} else
			throw new SurveyNotFoundException("Survey no encontrado idSurvey('"+ idSurvey +"')");
		
	}

	@Override
	public void delete(Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		if (surveySearch.isPresent())
			surveyDAO.delete(surveySearch.get());
		else
			throw new SurveyNotFoundException("Survey no encontrado idSurvey('"+ idSurvey +"')");
	}	
	
	@Override
	public Survey findById(Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		if (surveySearch.isPresent())
			return surveySearch.get();
		else
			throw new SurveyNotFoundException("Survey no encontrado idSurvey('"+ idSurvey +"')");
	}

	@Override
	public Set<SurveyDTOPost> findAllByCourse(Pageable page, Integer idCourse) throws NotFoundException {
		Course courseSearch = courseService.findById(idCourse);
		return surveyMapper.surveyGetDaoToDto(surveyDAO.findAllByCourse(page, courseSearch).stream().collect(Collectors.toSet()));
	}	

	@Override
	public Set<SurveyDTOPost> findAllByTag(PageRequest page, Integer idTag) throws NotFoundException {
		Tag tagSearch = tagService.findById(idTag);
		return surveyMapper.surveyGetDaoToDto(surveyDAO.findAllByTags(page, tagSearch).stream().collect(Collectors.toSet()));
	}
	
	@Override
	public void generateRamdomQuestions(Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		if (surveySearch.isPresent()) {
			Survey survey = surveySearch.get();
			List<Question> questions = new ArrayList<Question>(questionService.findAllbyTags(survey.getTags()).stream().collect(Collectors.toSet()));
			while (questions.size()>0 && survey.getPreguntas().size()<survey.getMaxPreguntas())
				survey.getPreguntas().add(questions.remove(ThreadLocalRandom.current().nextInt(0, questions.size()-1)));
			if (survey.getPreguntas().size()==survey.getMaxPreguntas()) {survey.setEsCerrado(true);}
			surveyDAO.save(survey);
		} else
			throw new SurveyNotFoundException("Survey no encontrado idSurvey('"+ idSurvey +"')");
		
	}
	






}
