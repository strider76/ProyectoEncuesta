package com.atsistemas.EncuestaProj.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.Tag;

@Repository
public interface SurveyDAO extends PagingAndSortingRepository<Survey, Integer> {

	Optional<Survey> findOneByIdCuestionario(Integer idCuestionario);
	Optional<Survey> findOneByIdentificador(String identificador);
	Page<Survey> findAllByCourse(Pageable page,Course course);
	Page<Survey> findAllByTags(Pageable page,Tag tag);
	
}
