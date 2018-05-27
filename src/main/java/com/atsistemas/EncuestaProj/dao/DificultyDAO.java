package com.atsistemas.EncuestaProj.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Dificulty;

@Repository
public interface DificultyDAO extends PagingAndSortingRepository<Dificulty, Integer> {

	public Optional<Dificulty> findOneByName(String name);
	
}
