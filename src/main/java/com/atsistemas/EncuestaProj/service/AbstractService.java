package com.atsistemas.EncuestaProj.service;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;

public interface AbstractService<T,ID extends Serializable> {

	T create(T model);
	
	Optional<T> findById(ID id);
	
	Set<T> findAll(Pageable pagina);
	
	void update(T model);
	
	void delete(T model);
	
}