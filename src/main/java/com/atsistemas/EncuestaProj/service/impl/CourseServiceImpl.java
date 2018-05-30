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
import com.atsistemas.EncuestaProj.excepciones.CourseNotfoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
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
	public Course findById(Integer id) throws NotFoundException {
		Optional<Course> courseSearch = courseDAO.findById(id);
		if (courseSearch.isPresent())
			return courseSearch.get();
		else
			throw new CourseNotfoundException("Course no encontrado idCourse('"+ id +"')");
	}

	@Override
	public Set<Course> findAll(Pageable pagina) {
		int numElementos = pagina.getPageSize();
		int numPagina = pagina.getPageNumber();
		return courseDAO.findAll(PageRequest.of(numPagina, numElementos)).stream().collect(Collectors.toSet());
	}

	@Override
	public void update(Integer idCourse, CourseDTO dto) throws NotFoundException {
		Optional<Course> courseSearch = courseDAO.findById(idCourse);
		if (courseSearch.isPresent()) {
			Course course = courseSearch.get();
			course.setName(dto.getName());
			courseDAO.save(course);
		} else
			throw new CourseNotfoundException("Course no encontrado idCourse('"+ idCourse +"')");
		
	}

	@Override
	public void delete(Integer idCourse) throws NotFoundException {
		Optional<Course> courseSearch = courseDAO.findById(idCourse);
		if (courseSearch.isPresent())
			courseDAO.delete(courseSearch.get());
		else
			throw new CourseNotfoundException("Course no encontrado idCourse('"+ idCourse +"')");
	}

	@Override
	public void addUserCourse(Integer idUser, Integer idCourse) throws NotFoundException {
		User userSearch = userService.findById(idUser);
		Optional<Course> courseSearch = courseDAO.findById(idCourse);
		if (courseSearch.isPresent()){
			Course course = courseSearch.get();
			course.getUsers().add(userSearch);
			courseDAO.save(course);
		} else {
			throw new CourseNotfoundException("Course no enconrtado con id('"+ idCourse +"')");
		}
		
	}

	@Override
	public void deleteUserCourse(Integer idUser, Integer idCourse) throws NotFoundException {
		User userSearch = userService.findById(idUser);
		Optional<Course> courseSearch = courseDAO.findById(idCourse);
		if (courseSearch.isPresent()){
			Course course = courseSearch.get();
			course.getUsers().remove(userSearch);
			courseDAO.save(course);
		} else {
			throw new CourseNotfoundException("Course no enconrtado con id('"+ idCourse +"')");
		}
		
	}

	@Override
	public Set<User> getAllUserCourse(Integer idCourse) throws NotFoundException {
		Optional<Course> courseSearch = courseDAO.findById(idCourse);
		if (courseSearch.isPresent()) {
			return courseSearch.get().getUsers();
		} else {
			throw new CourseNotfoundException("Course no enconrtado con id('"+ idCourse +"')");
		}
	}





}
