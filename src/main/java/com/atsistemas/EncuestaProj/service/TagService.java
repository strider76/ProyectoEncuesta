package com.atsistemas.EncuestaProj.service;

import java.util.Optional;

import com.atsistemas.EncuestaProj.controller.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.dto.TagDTO;
import com.atsistemas.EncuestaProj.model.Tag;

public interface TagService extends AbstractService<Tag, TagDTO, Integer> {

	Optional<Tag> findByName(String name);

	void asignSurvey(Tag tag, Integer idSurvey) throws SurveyNotFoundException;
	void removeSurvey(Tag tag, Integer idSurvey) throws SurveyNotFoundException;
	
}
