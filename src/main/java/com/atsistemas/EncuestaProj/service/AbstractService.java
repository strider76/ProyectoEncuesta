package com.atsistemas.EncuestaProj.service;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.atsistemas.EncuestaProj.excepciones.NotFoundException;

public interface AbstractService<D,T,ID extends Serializable> {

	D create(D model) throws NotFoundException;
	
	D findById(ID id) throws NotFoundException;
	
	Set<D> findAll(Pageable pagina);
	
	void update(ID id,T dto) throws NotFoundException;
	
	void delete(ID id) throws NotFoundException;
	
}
