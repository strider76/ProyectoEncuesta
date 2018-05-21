package com.atsistemas.EncuestaProj.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Cuestionario;

@Repository
public interface CuestionarioDAO extends PagingAndSortingRepository<Cuestionario, Integer> {

	Optional<Cuestionario> findOneByIdCuestionario(Integer idCuestionario);
	Optional<Cuestionario> findOneByIdentificador(String identificador);
	
}
