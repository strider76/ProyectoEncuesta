package com.atsistemas.EncuestaProj.service;

import java.util.List;
import java.util.Optional;

import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.User;

public interface CourseService {

	public void test();
	public Course addCourse(Course course);
	public List<Course> findAllCourses();
	public Optional<Course> findCourseById(Integer idCourse);
	public Optional<Course> findCourseByName(String name);
	public Boolean removeCourse(Integer id);
	public Optional<Course> updateCourse(Integer idCourse, Course course);
	public List<User> allUserCourse(Integer idCourse);
	
	
}
