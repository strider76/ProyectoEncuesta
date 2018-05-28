package com.atsistemas.EncuestaProj.dao;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Dificulty;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Tag;

@Repository
public interface QuestionDAO extends PagingAndSortingRepository<Question, Integer> {

	Page<Question> findAllByTag(Pageable arg0,Tag tag);
	Page<Question> findAllByDificulty(Pageable arg0,Dificulty tag);
	Set<Question>  findAllByTag(Set<Tag> tags);
}
