package com.atsistemas.EncuestaProj.service.impl;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.controller.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.dao.QuestionDAO;
import com.atsistemas.EncuestaProj.dto.QuestionDTO;
import com.atsistemas.EncuestaProj.dto.QuestionDTOPost;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
import com.atsistemas.EncuestaProj.mapper.QuestionMapper;
import com.atsistemas.EncuestaProj.model.Dificulty;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.Tag;
import com.atsistemas.EncuestaProj.service.DificultyService;
import com.atsistemas.EncuestaProj.service.QuestionService;
import com.atsistemas.EncuestaProj.service.SurveyService;
import com.atsistemas.EncuestaProj.service.TagService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private DificultyService dificultyService;
	
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	
	
	
	
	@Override
	public Question create(Question model) {
		return questionDAO.save(model);
	}

	@Override
	public Optional<Question> findById(Integer id) {
		return questionDAO.findById(id);
	}

	@Override
	public void update(Question model, QuestionDTO dto) throws DificultyNotFoundException, TagNotFoundException {
		Optional<Dificulty> dificultySearch = dificultyService.findById(dto.getIdDificulty());
		Optional<Tag> tagSearch = tagService.findById(dto.getIdTag());
		if (dificultySearch.isPresent() && tagSearch.isPresent()) {
			model.setName(dto.getName());
			model.setDificulty(dificultySearch.get());
			model.setTag(tagSearch.get());
			questionDAO.save(model);
		} else {
			if (!dificultySearch.isPresent()) {throw new DificultyNotFoundException("Dificulty no encontrada idDificulty('"+ dto.getIdDificulty() +"')");}
			if (!tagSearch.isPresent()) {throw new TagNotFoundException("Tag no encontrada idDificulty('"+ dto.getIdTag() +"')");}
		}
	}

	@Override
	public void delete(Question model) {
		questionDAO.delete(model);
	}

	@Override
	public Set<Question> findAll(Pageable pagina) {
		return questionDAO.findAll(pagina).stream().collect(Collectors.toSet());
	}	
	
	@Override
	public Set<QuestionDTOPost> findAllByTag(Integer idTag, Pageable page) throws TagNotFoundException {
		Optional<Tag> tagSearch = tagService.findById(idTag);
		if (tagSearch.isPresent())
			return questionMapper.QuestionGetDaoToDto(questionDAO.findAllByTag(page, tagSearch.get()).stream().collect(Collectors.toSet()));
		else
			throw new TagNotFoundException("Tag No encontrado idTag('"+ idTag +"')");
	}

	@Override
	public Set<QuestionDTOPost> findAllByDificulty(Integer idDificulty, Pageable page) throws DificultyNotFoundException {
		Optional<Dificulty> dificultySearch = dificultyService.findById(idDificulty);
		if (dificultySearch.isPresent())
			return questionMapper.QuestionGetDaoToDto(questionDAO.findAllByDificulty(page, dificultySearch.get()).stream().collect(Collectors.toSet()));
		else
			throw new DificultyNotFoundException("Dificultad no encontrada idDificultad('"+ idDificulty +"')");
	}

	@Override
	public void assignSurvey(Question question, Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyService.findById(idSurvey);
		if (surveySearch.isPresent() && surveySearch.get().getPreguntas().size()<surveySearch.get().getMaxPreguntas()){
			question.getCuestionarios().add(surveySearch.get());
			questionDAO.save(question);
		} else
			throw new SurveyNotFoundException("Survey no encontrada idSurvey('"+ idSurvey +"') o está completo");
			
		
	}

	@Override
	public void removeSurvey(Question question, Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyService.findById(idSurvey);
		if (surveySearch.isPresent()){
			question.getCuestionarios().remove(surveySearch.get());
			questionDAO.save(question);
		} else
			throw new SurveyNotFoundException("Survey no encontrada idSurvey('"+ idSurvey +"')");
		
	}

	@Override
	public Set<Question> findAllbyTags(Set<Tag> tags) {
		return questionDAO.findAllByTag(tags);
	}





}
