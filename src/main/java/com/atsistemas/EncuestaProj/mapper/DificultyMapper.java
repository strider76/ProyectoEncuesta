package com.atsistemas.EncuestaProj.mapper;

import java.util.Set;

import com.atsistemas.EncuestaProj.dto.DificultyDTO;
import com.atsistemas.EncuestaProj.dto.DificultyDTOPost;
import com.atsistemas.EncuestaProj.model.Dificulty;

public interface DificultyMapper {

	Dificulty dificultyDtoToDao(DificultyDTO dificultyDTO);
	DificultyDTOPost dificultyDaoToDto(Dificulty dificulty);
	Set<Dificulty> dificultyGetsDtoToDao(Set<DificultyDTO> dificultiesDTO);
	Set<DificultyDTOPost> dificultyGetsDaoToDto(Set<Dificulty> dificulties);
	
}
