package com.atsistemas.EncuestaProj.service;

import java.util.Optional;

import com.atsistemas.EncuestaProj.dto.DificultyDTO;
import com.atsistemas.EncuestaProj.model.Dificulty;

public interface DificultyService extends AbstractService<Dificulty, DificultyDTO, Integer> {

	Optional<Dificulty> findByName(String name);
	
}
