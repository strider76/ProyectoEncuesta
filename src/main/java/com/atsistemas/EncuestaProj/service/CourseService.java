package com.atsistemas.EncuestaProj.service;

import java.util.List;

import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.excepciones.CourseNotfoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.UserNotFoundException;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.User;

public interface CourseService extends AbstractService<Course, CourseDTO, Integer> {

	public void addUserCourse(Integer idUser, Integer course) throws UserNotFoundException, NotFoundException;
	public void deleteUserCourse(Integer user, Integer course) throws UserNotFoundException, NotFoundException;
	public List<User> getAllUserCourse (Integer idCourse) throws NotFoundException;
	public List<Survey> getAllSurveyCourse(Integer idCourse) throws CourseNotfoundException;
	public Survey createSurvey(Survey surveyDtoToDao,Integer idCourse) throws NotFoundException;
	
}
