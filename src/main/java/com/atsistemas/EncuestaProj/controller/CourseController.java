package com.atsistemas.EncuestaProj.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.dto.CourseDTOPost;
import com.atsistemas.EncuestaProj.dto.UserDTOPost;
import com.atsistemas.EncuestaProj.excepciones.CourseNotfoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.UserNotFoundException;
import com.atsistemas.EncuestaProj.mapper.CourseMapper;
import com.atsistemas.EncuestaProj.mapper.UserMapper;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.service.CourseService;


@RestController()
@RequestMapping(value="/course")
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	CourseMapper courseMapper;
	
	@GetMapping
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public Set<CourseDTOPost> findAll(@RequestParam(defaultValue="0",required=false) Integer page,
									  @RequestParam(defaultValue="10", required=false) Integer size){
		
		return courseMapper.courseGetDaoToDto(courseService.findAll(PageRequest.of(page, size)));

	}
	
	@GetMapping("/{idCourse}")
	public CourseDTOPost findById(@PathVariable Integer idCourse) throws CourseNotfoundException {
		Optional<Course> courseSearch =courseService.findById(idCourse);
		if (courseSearch.isPresent())
			return courseMapper.courseDaoToDto(courseSearch.get());
		else
			throw new CourseNotfoundException("No se encuentra el curso con id " + idCourse);
	}
	
	@DeleteMapping("/{idCourse}")
	@ResponseStatus(code=HttpStatus.OK)
	public void delete(@PathVariable Integer idCourse) throws CourseNotfoundException {
		Optional<Course> courseSearch = courseService.findById(idCourse);
		if (courseSearch.isPresent())
			courseService.delete(courseSearch.get());
		else
			throw new CourseNotfoundException("No se encuentra el curso con id " + idCourse);
	}
	
	@PostMapping
	public ResponseEntity<CourseDTOPost> create(@RequestBody CourseDTO courseDTO ) throws NotFoundException {
		CourseDTOPost courseAdded = courseMapper.courseDaoToDto(courseService.create(courseMapper.courseDtoToDao(courseDTO)));
		return new ResponseEntity<CourseDTOPost>(courseAdded, HttpStatus.CREATED);
	}
	
	@PutMapping("/{idCourse}")
	@ResponseStatus(code=HttpStatus.OK)
	public void update(@PathVariable Integer idCourse, @RequestBody CourseDTOPost courseModified) throws NotFoundException {
		Optional<Course> courseSearch = courseService.findById(idCourse);
		if (courseSearch.isPresent()){
			courseService.update(courseSearch.get(), courseModified);
		} else {
			throw new CourseNotfoundException("No se encuentra el curso con id " + idCourse);
		}
	}
	
	@PostMapping("/{idCourse}/user/{idUser}")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public void addUserToCourse(@PathVariable Integer idCourse, @PathVariable Integer idUser) throws CourseNotfoundException, UserNotFoundException {
		Optional<Course> courseSearch = courseService.findById(idCourse);
		if (courseSearch.isPresent()){
			courseService.addUserCourse(idUser, courseSearch.get());
		} else {
			throw new CourseNotfoundException("No se encuentra el curso con id " + idCourse);
		}
	}
	
	@DeleteMapping("/{idCourse}/user/{idUser}")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public void deleteUserToCourse(@PathVariable Integer idCourse, @PathVariable Integer idUser) throws CourseNotfoundException, UserNotFoundException {
		Optional<Course> courseSearch = courseService.findById(idCourse);
		if (courseSearch.isPresent()){
			courseService.deleteUserCourse(idUser, courseSearch.get());
		} else {
			throw new CourseNotfoundException("No se encuentra el curso con id " + idCourse);
		}
	}	
	
	@GetMapping("/{idCourse}/user")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public Set<UserDTOPost> getUsersCourse(@PathVariable Integer idCourse) throws CourseNotfoundException {
		Optional<Course> courseSearch = courseService.findById(idCourse);
		if (courseSearch.isPresent()){
			return userMapper.userGetDaoToDto(courseService.getAllUserCourse(courseSearch.get()));
		} else {
			throw new CourseNotfoundException("No se encuentra el curso con id " + idCourse);
		}		
	}
}
