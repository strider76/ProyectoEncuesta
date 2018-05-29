package com.atsistemas.EncuestaProj.service;


import com.atsistemas.EncuestaProj.dto.DificultyDTO;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.model.Dificulty;

public interface DificultyService extends AbstractService<Dificulty, DificultyDTO, Integer> {

	Dificulty findByName(String name) throws DificultyNotFoundException;
	
}
