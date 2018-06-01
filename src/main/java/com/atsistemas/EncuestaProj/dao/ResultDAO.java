package com.atsistemas.EncuestaProj.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Result;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.User;

@Repository
public interface ResultDAO extends PagingAndSortingRepository<Result, Integer> {
	List<Question> findQuestionbyUserAndCuestionario(User user,Survey survey);
	List<Result> findAllByUserAndCuestionario(User user,Survey survey);
	Integer countByUserAndCuestionarioAndEsCorrectoTrue(User user,Survey survey);
}
