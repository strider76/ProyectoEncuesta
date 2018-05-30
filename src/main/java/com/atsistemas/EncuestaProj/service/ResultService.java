package com.atsistemas.EncuestaProj.service;

import java.util.List;

import com.atsistemas.EncuestaProj.dto.QuestionAnswerDTO;
import com.atsistemas.EncuestaProj.dto.ResultCourseDTO;
import com.atsistemas.EncuestaProj.dto.ResultSurveyDTO;



public interface ResultService  {

	QuestionAnswerDTO createUserQuestionSurvey(Integer idUser, Integer idSurvey);
	void ResponseUserQuestionSurvey(Integer idUser, Integer idSurvey, Integer idQuestion, Integer idAnswer);
	List<com.atsistemas.EncuestaProj.dto.QuestionAnswerDTO> createUserAllQuestionSurvey(Integer idUser,	Integer idSurvey);
	ResultCourseDTO getResultUserCourse(Integer idUser, Integer idCourse);
	ResultSurveyDTO getResultUserSurvey(Integer idUser, Integer idSurvey);
	
}
