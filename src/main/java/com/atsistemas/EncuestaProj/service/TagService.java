package com.atsistemas.EncuestaProj.service;

import org.springframework.beans.factory.InitializingBean;

import com.atsistemas.EncuestaProj.dto.TagDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
import com.atsistemas.EncuestaProj.model.Tag;

public interface TagService extends AbstractService<Tag, TagDTO, Integer>, InitializingBean {

	Tag findByName(String name) throws TagNotFoundException;

	void asignSurvey(Integer idTag, Integer idSurvey) throws SurveyNotFoundException, NotFoundException;
	void removeSurvey(Integer idTag, Integer idSurvey) throws SurveyNotFoundException, NotFoundException;
	
}
