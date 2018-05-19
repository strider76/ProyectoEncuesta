package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Respuesta;

@Repository
public interface RespuestaDAO extends PagingAndSortingRepository<Respuesta, Integer> {

}
