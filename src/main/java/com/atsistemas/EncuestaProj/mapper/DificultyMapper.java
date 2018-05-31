package com.atsistemas.EncuestaProj.mapper;

import java.util.List;

import com.atsistemas.EncuestaProj.dto.DificultyDTO;
import com.atsistemas.EncuestaProj.dto.DificultyDTOPost;
import com.atsistemas.EncuestaProj.model.Dificulty;

public interface DificultyMapper {

	Dificulty dificultyDtoToDao(DificultyDTO dificultyDTO);
	DificultyDTOPost dificultyDaoToDto(Dificulty dificulty);
	List<Dificulty> dificultyGetsDtoToDao(List<DificultyDTO> dificultiesDTO);
	List<DificultyDTOPost> dificultyGetsDaoToDto(List<Dificulty> dificulties);
	
}
