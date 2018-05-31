package com.atsistemas.EncuestaProj.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Answer;
import com.atsistemas.EncuestaProj.model.Question;


@Repository
public interface AnswerDAO extends PagingAndSortingRepository<Answer, Integer> {

	List<Answer> findAllByQuestion(Question question);
	
}
