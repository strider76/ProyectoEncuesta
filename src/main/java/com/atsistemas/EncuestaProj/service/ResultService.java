package com.atsistemas.EncuestaProj.service;

import java.util.List;

import com.atsistemas.EncuestaProj.dto.QuestionResAllDTO;
import com.atsistemas.EncuestaProj.dto.QuestionResDTO;
import com.atsistemas.EncuestaProj.dto.ResultCourseDTO;
import com.atsistemas.EncuestaProj.dto.ResultSurveyDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyFinishedException;



public interface ResultService  {

	QuestionResDTO createUserSurveyQuestion(Integer idUser, Integer idSurvey) throws NotFoundException, SurveyFinishedException;
	void ResponseUserQuestionSurvey(Integer idQuestionRes, Integer idAnswer) throws NotFoundException;
	List<QuestionResDTO> createUserAllQuestionSurvey(Integer idUser,	Integer idSurvey) throws NotFoundException;
	List<ResultCourseDTO> getResultUserCourse(Integer idUser, Integer idCourse) throws NotFoundException;
	List<ResultSurveyDTO> getResultUserSurvey(Integer idUser, Integer idSurvey) throws NotFoundException;
	void ResponseUserAllQuestionSurvey(Integer idUser, Integer idSurvey, List<QuestionResAllDTO> questionResAllDto) throws NotFoundException;
	
}
