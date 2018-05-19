package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Dificultad;

@Repository
public interface DificultadDAO extends PagingAndSortingRepository<Dificultad, Integer> {

}
