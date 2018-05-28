package com.atsistemas.EncuestaProj.service;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.atsistemas.EncuestaProj.excepciones.NotFoundException;

public interface AbstractService<T,D,ID extends Serializable> {

	T create(T model) throws NotFoundException;
	
	Optional<T> findById(ID id);
	
	Set<T> findAll(Pageable pagina);
	
	void update(T model,D dto) throws NotFoundException;
	
	void delete(T model);
	
}
