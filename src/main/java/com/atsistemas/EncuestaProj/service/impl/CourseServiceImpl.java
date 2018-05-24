package com.atsistemas.EncuestaProj.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.CourseDAO;
import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.Cuestionario;
import com.atsistemas.EncuestaProj.model.User;
import com.atsistemas.EncuestaProj.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO courseDAO;
	
	@Override
	public Course create(Course model) {
		return courseDAO.save(model);
	}

	@Override
	public Optional<Course> findById(Integer id) {
		return courseDAO.findById(id);
	}

	@Override
	public Set<Course> findAll(Pageable pagina) {
		int numElementos = pagina.getPageSize();
		int numPagina = pagina.getPageNumber();
		return courseDAO.findAll(PageRequest.of(numPagina, numElementos)).stream().collect(Collectors.toSet());
	}

	@Override
	public void update(Course model, CourseDTO dto) {
		model.setName(dto.getName());
		courseDAO.save(model);
		
	}

	@Override
	public void delete(Course model) {
		courseDAO.delete(model);
		
	}

	@Override
	public void addUserCourse(User user, Integer idCourse) {
		Optional<Course> courseSearch = courseDAO.findById(idCourse);
		if (courseSearch.isPresent()){
			Course curCourse = courseSearch.get();
			curCourse.getUsers().add(user);
			courseDAO.save(curCourse);
		}
		
	}

	@Override
	public void deleteUserCourse(User user, Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<User> getAllUserCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCuestionarioCourse(Cuestionario cuestionario, Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCuestionarioCourse(Cuestionario cuestionario, Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Cuestionario> getAllCuestionarioCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}



}
