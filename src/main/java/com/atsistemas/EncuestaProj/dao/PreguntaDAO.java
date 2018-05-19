package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Pregunta;

@Repository
public interface PreguntaDAO extends PagingAndSortingRepository<Pregunta, Integer> {

}
