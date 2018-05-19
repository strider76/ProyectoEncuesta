package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Result;

@Repository
public interface ResultDAO extends PagingAndSortingRepository<Result, Integer> {

}
