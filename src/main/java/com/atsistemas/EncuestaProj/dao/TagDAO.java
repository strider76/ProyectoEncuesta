package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Tag;

@Repository
public interface TagDAO extends PagingAndSortingRepository<Tag, Integer> {

}
