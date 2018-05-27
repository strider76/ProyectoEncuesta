package com.atsistemas.EncuestaProj.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.CourseDAO;
import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.excepciones.UserNotFoundException;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.User;
import com.atsistemas.EncuestaProj.service.CourseService;
import com.atsistemas.EncuestaProj.service.UserService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO courseDAO;
	
	@Autowired
	private UserService userService;
	
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
	public void addUserCourse(Integer user, Course course) throws UserNotFoundException {
		Optional<User> userSearch = userService.findById(user);
		if (userSearch.isPresent()){
			course.getUsers().add(userSearch.get());
			courseDAO.save(course);
		} else {
			throw new UserNotFoundException("Usuario no enconrtado con id('"+ user +"')");
		}
		
	}

	@Override
	public void deleteUserCourse(Integer idUser, Course course) throws UserNotFoundException {
		Optional<User> userSearch = userService.findById(idUser);
		if (userSearch.isPresent()){
			course.getUsers().remove(userSearch.get());
			courseDAO.save(course);
		} else {
			throw  new UserNotFoundException("Usuario no enconrtado con id('"+ idUser +"')");
		}
		
	}

	@Override
	public Set<User> getAllUserCourse(Course course) {
		Set<User> usersCourse = new HashSet<>();
		for (User user : course.getUsers()) 
			usersCourse.add(user);
		return usersCourse;
	}





}
