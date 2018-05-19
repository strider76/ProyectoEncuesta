package com.atsistemas.EncuestaProj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.UserCourse;

@Repository
public interface UserCourseDAO extends PagingAndSortingRepository<UserCourse, Integer> {

}
