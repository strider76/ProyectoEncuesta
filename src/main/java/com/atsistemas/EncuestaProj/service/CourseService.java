package com.atsistemas.EncuestaProj.service;

import java.util.Set;

import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.Cuestionario;
import com.atsistemas.EncuestaProj.model.User;

public interface CourseService extends AbstractService<Course, CourseDTO, Integer> {

	public void addUserCourse(User user, Integer idCourse);
	public void deleteUserCourse(User user, Course course);
	public Set<User> getAllUserCourse (Course course);
	public void addCuestionarioCourse(Cuestionario cuestionario, Course course);
	public void deleteCuestionarioCourse(Cuestionario cuestionario, Course course);
	public Set<Cuestionario> getAllCuestionarioCourse(Course course);
	
}
