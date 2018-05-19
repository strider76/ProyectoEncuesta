package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.CuestionarioTag;

@Repository
public interface CuestionarioTagDAO extends PagingAndSortingRepository<CuestionarioTag, Integer> {

}
