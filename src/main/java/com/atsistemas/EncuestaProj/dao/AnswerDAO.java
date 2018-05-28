package com.atsistemas.EncuestaProj.dao;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Answer;
import com.atsistemas.EncuestaProj.model.Question;


@Repository
public interface AnswerDAO extends PagingAndSortingRepository<Answer, Integer> {

	Set<Answer> findAllByQuestion(Question question);
	
}
