package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.Course;

@Repository
public interface CourseDAO extends PagingAndSortingRepository<Course, Integer> {

}
