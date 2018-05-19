package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.CuestionarioPregunta;

@Repository
public interface CuestionarioPreguntaDAO extends PagingAndSortingRepository<CuestionarioPregunta, Integer> {

}
