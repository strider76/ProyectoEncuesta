package com.atsistemas.EncuestaProj.service;


import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.atsistemas.EncuestaProj.dto.DificultyDTO;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.model.Dificulty;
import com.atsistemas.EncuestaProj.model.Question;

public interface DificultyService extends AbstractService<Dificulty, DificultyDTO, Integer> {

	Dificulty findByName(String name) throws DificultyNotFoundException;

	Set<Question> findQuestionsByDificulty(Pageable page, Integer idDificulty) throws DificultyNotFoundException;
	
}
