package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Cuestionario;

@Repository
public interface CuestionarioDAO extends PagingAndSortingRepository<Cuestionario, Integer> {

}
