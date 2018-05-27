package com.atsistemas.EncuestaProj.service;

import java.util.Set;

import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.excepciones.UserNotFoundException;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.User;

public interface CourseService extends AbstractService<Course, CourseDTO, Integer> {

	public void addUserCourse(Integer idUser, Course course) throws UserNotFoundException;
	public void deleteUserCourse(Integer user, Course course) throws UserNotFoundException;
	public Set<User> getAllUserCourse (Course course);
	
}
