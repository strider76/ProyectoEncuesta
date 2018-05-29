package com.atsistemas.EncuestaProj.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.TagDAO;
import com.atsistemas.EncuestaProj.dto.TagDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
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
	public Tag findById(Integer id) throws TagNotFoundException {
		Optional<Tag> tagSearch = tagDAO.findById(id);
		if (tagSearch.isPresent())
			return tagSearch.get();
		else
			throw new TagNotFoundException("Tag no encontrado idTag('"+ id + "')");
	}

	@Override
	public Set<Tag> findAll(Pageable pagina) {
		int pageNumber = pagina.getPageNumber();
		int pageSize = pagina.getPageSize();
		return tagDAO.findAll(PageRequest.of(pageNumber, pageSize)).stream().collect(Collectors.toSet());
	}

	@Override
	public void update(Integer idTag, TagDTO dto) throws TagNotFoundException {
		Optional<Tag> tagSearch = tagDAO.findById(idTag);
		if (tagSearch.isPresent()){
			Tag tag = tagSearch.get();
			tag.setName(dto.getName());
			tagDAO.save(tag);
		} else 
			throw new TagNotFoundException("Tag no encontrado idTag('"+ idTag + "')");
	}

	@Override
	public void delete(Integer idTag) throws TagNotFoundException {
		Optional<Tag> tagSearch = tagDAO.findById(idTag);
		if (tagSearch.isPresent())
			tagDAO.delete(tagSearch.get());
		else
			throw new TagNotFoundException("Tag no encontrado idTag('"+ idTag + "')");

	}

	@Override
	public Tag findByName(String name) throws TagNotFoundException {
		Optional<Tag> tagSearch = tagDAO.findOneByName(name);
		if (tagSearch.isPresent())
			return tagSearch.get();
		else
			throw new TagNotFoundException("Tag no encontrado name('"+ name + "')");

	}

	@Override
	public void asignSurvey(Integer idTag, Integer idSurvey) throws NotFoundException {
		Optional<Tag> tagSearch = tagDAO.findById(idTag);
		if (tagSearch.isPresent()){
			Tag tag = tagSearch.get();
			tag.getCuestionarios().add(surveyService.findById(idSurvey));
			tagDAO.save(tag);
		} else 
			throw new TagNotFoundException("Tag No Encontrado idTag('"+ idTag +"')");
	}

	@Override
	public void removeSurvey(Integer idTag, Integer idSurvey) throws NotFoundException {
		Optional<Tag> tagSearch = tagDAO.findById(idTag);
		if (tagSearch.isPresent()){
			Tag tag = tagSearch.get();
			tag.getCuestionarios().remove(surveyService.findById(idSurvey));
			tagDAO.save(tag);
		} else 
			throw new TagNotFoundException("Tag No Encontrado idTag('"+ idTag +"')");
		
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
