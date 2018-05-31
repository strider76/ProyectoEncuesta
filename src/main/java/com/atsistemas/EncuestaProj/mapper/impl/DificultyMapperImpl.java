package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.EncuestaProj.dto.DificultyDTO;
import com.atsistemas.EncuestaProj.dto.DificultyDTOPost;
import com.atsistemas.EncuestaProj.mapper.DificultyMapper;
import com.atsistemas.EncuestaProj.model.Dificulty;

@Component
public class DificultyMapperImpl implements DificultyMapper {

	@Autowired
	DozerBeanMapper dificultyMapper;
	
	@Override
	public Dificulty dificultyDtoToDao(DificultyDTO dificultyDTO) {
		return dificultyMapper.map(dificultyDTO, Dificulty.class);
	}

	@Override
	public DificultyDTOPost dificultyDaoToDto(Dificulty dificulty) {
		return dificultyMapper.map(dificulty, DificultyDTOPost.class);
	}

	@Override
	public List<Dificulty> dificultyGetsDtoToDao(List<DificultyDTO> dificultiesDTO) {
		List<Dificulty> dificulties = new ArrayList<>();
		for (DificultyDTO dificulty : dificultiesDTO) 
			dificulties.add(dificultyDtoToDao(dificulty));
		return dificulties;
	}

	@Override
	public List<DificultyDTOPost> dificultyGetsDaoToDto(List<Dificulty> dificulties) {
		
		List<DificultyDTOPost> dificultyList = new ArrayList<>();
		for (Dificulty dificulty : dificulties) 
			dificultyList.add(dificultyDaoToDto(dificulty));
		return dificultyList;
		
	}

}
