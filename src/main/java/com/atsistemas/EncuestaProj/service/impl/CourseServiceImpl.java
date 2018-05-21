package com.atsistemas.EncuestaProj.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.CourseDAO;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.User;
import com.atsistemas.EncuestaProj.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService, InitializingBean {

	@Autowired
	private CourseDAO courseDAO;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		test();
		
	}
	
	@Override
	public void test() {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public Course addCourse(Course course) {
		return courseDAO.save(course);
	}

	@Override
	public List<Course> findAllCourses() {
		List<Course> allCourses = new ArrayList<>();
		Iterator<Course> iterador = courseDAO.findAll().iterator();
		while (iterador.hasNext())
			allCourses.add(iterador.next());
		return allCourses;
	}

	@Override
	public Optional<Course> findCourseById(Integer idCourse) {
		return courseDAO.findById(idCourse);
	}

	@Override
	public Optional<Course> findCourseByName(String name) {
		return courseDAO.findOneByName(name);
	}

	@Override
	public Boolean removeCourse(Integer id) {
		Optional<Course> courseSearch = findCourseById(id);
		if (courseSearch.isPresent()) {
			courseDAO.delete(courseSearch.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Optional<Course> updateCourse(Integer idCourse, Course course) {
		Optional<Course> courseSearch = findCourseById(idCourse);
		if (courseSearch.isPresent()) {
			Course courseOrigin = courseSearch.get();
			courseOrigin.setName(course.getName());
			courseOrigin.setCuestionarios(course.getCuestionarios());
			courseOrigin.setUsers(course.getUsers());
			courseOrigin = courseDAO.save(courseOrigin);
			return Optional.of(courseOrigin);
		} else {
			return Optional.empty();
		}
		
	}

	@Override
	public List<User> allUserCourse(Integer idCourse) {
		List<User> usersCourse = new ArrayList<>();
		Optional<Course> courseSearch = findCourseById(idCourse);
		if (courseSearch.isPresent()) {
			usersCourse = courseSearch.get().getUsers();
		}
		return usersCourse;
	}



}
