package com.atsistemas.EncuestaProj.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.controller.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.dao.TagDAO;
import com.atsistemas.EncuestaProj.dto.TagDTO;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.Tag;
import com.atsistemas.EncuestaProj.service.SurveyService;
import com.atsistemas.EncuestaProj.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagDAO tagDAO;
	
	@Autowired
	SurveyService surveyService;
	
	
	@Override
	public Tag create(Tag model) {
		return tagDAO.save(model);
	}

	@Override
	public Optional<Tag> findById(Integer id) {
		return tagDAO.findById(id);
	}

	@Override
	public Set<Tag> findAll(Pageable pagina) {
		int pageNumber = pagina.getPageNumber();
		int pageSize = pagina.getPageSize();
		return tagDAO.findAll(PageRequest.of(pageNumber, pageSize)).stream().collect(Collectors.toSet());
	}

	@Override
	public void update(Tag model, TagDTO dto) {
		model.setName(dto.getName());
		tagDAO.save(model);
	}

	@Override
	public void delete(Tag model) {
		tagDAO.delete(model);

	}

	@Override
	public Optional<Tag> findByName(String name) {
		return tagDAO.findOneByName(name);
	}

	@Override
	public void asignSurvey(Tag tag, Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyService.findById(idSurvey);
		if (surveySearch.isPresent()){
			tag.getCuestionarios().add(surveySearch.get());
			tagDAO.save(tag);
		} else 
			throw new SurveyNotFoundException("Survey No Encontrado idSurvey('"+ idSurvey +"')");
	}

	@Override
	public void removeSurvey(Tag tag, Integer idSurvey) throws SurveyNotFoundException {
		Optional<Survey> surveySearch = surveyService.findById(idSurvey);
		if (surveySearch.isPresent()){
			tag.getCuestionarios().remove(surveySearch.get());
			tagDAO.save(tag);
		} else 
			throw new SurveyNotFoundException("Survey No Encontrado idSurvey('"+ idSurvey +"')");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initTags();
		
	}

	private void initTags() {
		
		Tag tag1 = new Tag();
		Tag tag2 = new Tag();
		Tag tag3 = new Tag();
		
		tag1.setName("jpa");
		tag2.setName("spring");
		tag3.setName("hibernate");
		
		tagDAO.save(tag1);
		tagDAO.save(tag2);
		tagDAO.save(tag3);
		
	}
	
	

}
