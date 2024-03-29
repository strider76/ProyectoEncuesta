package com.atsistemas.EncuestaProj.controller;


import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.dto.CourseDTOPost;
import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.dto.SurveyDTOPost;
import com.atsistemas.EncuestaProj.dto.UserDTOPost;
import com.atsistemas.EncuestaProj.excepciones.CourseNotfoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.mapper.CourseMapper;
import com.atsistemas.EncuestaProj.mapper.SurveyMapper;
import com.atsistemas.EncuestaProj.mapper.UserMapper;
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
	
	@Autowired
	SurveyMapper surveyMapper;
	
	@GetMapping
	public List<CourseDTOPost> findAll(@RequestParam(defaultValue="0",required=false) Integer page,
									  @RequestParam(defaultValue="10", required=false) Integer size){
		
		return courseMapper.courseGetDaoToDto(courseService.findAll(PageRequest.of(page, size)));

	}
	
	@GetMapping("/{idCourse}")
	public CourseDTOPost findById(@PathVariable Integer idCourse) throws NotFoundException {
		return courseMapper.courseDaoToDto(courseService.findById(idCourse));
	}
	
	@GetMapping("/{idCourse}/user")
	public List<UserDTOPost> getUsersCourse(@PathVariable Integer idCourse) throws NotFoundException {
		return userMapper.userGetDaoToDto(courseService.getAllUserCourse(idCourse));
	}
	
	@GetMapping("/{idCourse}/survey")
	public List<SurveyDTOPost> getSurveyCourse(@PathVariable Integer idCourse) throws CourseNotfoundException {
		return surveyMapper.surveyGetDaoToDto(courseService.getAllSurveyCourse(idCourse));
	}
	
	
	@DeleteMapping("/{idCourse}")
	public void delete(@PathVariable Integer idCourse) throws NotFoundException {
		courseService.delete(idCourse);
	}
	
	@PostMapping
	public ResponseEntity<CourseDTOPost> create(@RequestBody CourseDTO courseDTO ) throws NotFoundException {
		CourseDTOPost courseAdded = courseMapper.courseDaoToDto(courseService.create(courseMapper.courseDtoToDao(courseDTO)));
		return new ResponseEntity<CourseDTOPost>(courseAdded, HttpStatus.CREATED);
	}
	
	@PutMapping("/{idCourse}")
	public void update(@PathVariable Integer idCourse, @RequestBody CourseDTO courseModified) throws NotFoundException {
		courseService.update(idCourse, courseModified);
	}
	
	@PostMapping("/{idCourse}/user/{idUser}")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public void addUserToCourse(@PathVariable Integer idCourse, @PathVariable Integer idUser) throws NotFoundException {
		courseService.addUserCourse(idUser, idCourse);
	}
	
	@PostMapping("/{idCourse}/survey")
	@ResponseStatus(code=HttpStatus.CREATED)
	public SurveyDTOPost create (@PathVariable Integer idCourse,@RequestBody SurveyDTO suerveyDTO) throws NotFoundException {
		return surveyMapper.surveyDaoToDto(courseService.createSurvey(surveyMapper.surveyDtoToDao(suerveyDTO),idCourse));
	}
	
	@DeleteMapping("/{idCourse}/user/{idUser}")
	public void deleteUserToCourse(@PathVariable Integer idCourse, @PathVariable Integer idUser) throws NotFoundException {
		courseService.deleteUserCourse(idUser, idCourse);
	}	
	
	
	
}
