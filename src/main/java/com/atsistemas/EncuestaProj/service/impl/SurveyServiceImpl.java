package com.atsistemas.EncuestaProj.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.SurveyDAO;
import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyExcedQuestions;
import com.atsistemas.EncuestaProj.excepciones.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyQuestionRepeatedException;
import com.atsistemas.EncuestaProj.mapper.SurveyMapper;
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
	public void generateRamdomQuestions(Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		if (surveySearch.isPresent()) {
			Survey survey = surveySearch.get();
			List<Question> questionTags = questionService.findQuestionByTag(survey.getTags()).stream().collect(Collectors.toList());
			
			while (questionTags.size()>0 && survey.getPreguntas().size()<survey.getMaxPreguntas())
				survey.getPreguntas().add(questionTags.remove(ThreadLocalRandom.current().nextInt(0, questionTags.size())));
			
			if (survey.getPreguntas().size()==survey.getMaxPreguntas()) {survey.setEsCerrado(true);}
			surveyDAO.save(survey);
		} else
			throw new SurveyNotFoundException("Survey no encontrado idSurvey('"+ idSurvey +"')");
		
	}


	@Override
	public Set<Question> findQuestionBySurvey(Pageable page, Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		if (surveySearch.isPresent())
			return subSetQuestion(page,surveySearch.get().getPreguntas());
		else
			throw new SurveyNotFoundException("Survey no encontrada idSurvey('"+ idSurvey +"')");
	}
	

	private Set<Question> subSetQuestion (Pageable page, Set<Question> inicial) {
		List<Question> list = new ArrayList<>(inicial);
		Integer posicionInicial = page.getPageSize()*page.getPageNumber();
		Integer posicionFinal = (list.size() > (posicionInicial+page.getPageSize()))?posicionInicial+page.getPageSize()-1:list.size();
		return new LinkedHashSet<>(list.subList(posicionInicial, posicionFinal));
		
	}

	@Override
	public Set<Tag> findTagBySurvey(Pageable page, Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		if (surveySearch.isPresent())
			return subSetTag(page,surveySearch.get().getTags());
		else
			throw new SurveyNotFoundException("Survey no encontrada idSurvey('"+ idSurvey +"')");
	}
	
	private Set<Tag> subSetTag (Pageable page, Set<Tag> inicial) {
		List<Tag> list = new ArrayList<>(inicial);
		Integer posicionInicial = page.getPageSize()*page.getPageNumber();
		Integer posicionFinal = (list.size() > (posicionInicial+page.getPageSize()))?posicionInicial+page.getPageSize()-1:list.size();
		return new LinkedHashSet<>(list.subList(posicionInicial, posicionFinal));
		
	}

	@Override
	public void addTagToSurvey(Integer idSurvey, Integer idTag) throws NotFoundException {
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		Tag tag = tagService.findById(idTag);
		if (surveySearch.isPresent()){
			Survey survey = surveySearch.get();
			survey.getTags().add(tag);
			surveyDAO.save(survey);
		} else 
			throw new SurveyNotFoundException("Survey no encontrada idSurvey('"+idSurvey+"')");
		
	}

	@Override
	public void removeTagToSurvey(Integer idSurvey, Integer idTag) throws NotFoundException {
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		Tag tag = tagService.findById(idTag);
		if (surveySearch.isPresent()){
			Survey survey = surveySearch.get();
			survey.getTags().remove(tag);
			surveyDAO.save(survey);
		} else 
			throw new SurveyNotFoundException("Survey no encontrada idSurvey('"+idSurvey+"')");
		
	}

	@Override
	public void addQuestionToSurvey(Integer idSurvey, Integer idQuestion) throws NotFoundException, SurveyExcedQuestions, SurveyQuestionRepeatedException {
		
		Optional<Survey> surveySearch = surveyDAO.findById(idSurvey);
		if (surveySearch.isPresent()){
			Survey survey = surveySearch.get();
			if (survey.getMaxPreguntas()>survey.getPreguntas().size() || survey.getPreguntas().contains(questionService.findById(idQuestion))){
				survey.getPreguntas().add(questionService.findById(idQuestion));
				surveyDAO.save(survey);
			} else 
				if (survey.getMaxPreguntas()==survey.getPreguntas().size())
					throw new SurveyExcedQuestions("Survey no admite mas preguntas idSurvey('"+ idSurvey +"')");
				else
					throw new SurveyQuestionRepeatedException("Question "+ idQuestion +" ya insertada en Survey " +idSurvey);
				
		} else
			throw new SurveyNotFoundException("Survey no encontrado idSurvey('"+ idSurvey +"')");
		
	}






}
