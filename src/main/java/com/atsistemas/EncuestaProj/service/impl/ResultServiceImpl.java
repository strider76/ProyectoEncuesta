package com.atsistemas.EncuestaProj.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.ResultDAO;
import com.atsistemas.EncuestaProj.dto.QuestionAnswerDTO;
import com.atsistemas.EncuestaProj.dto.ResultCourseDTO;
import com.atsistemas.EncuestaProj.dto.ResultSurveyDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.User;
import com.atsistemas.EncuestaProj.service.ResultService;
import com.atsistemas.EncuestaProj.service.SurveyService;
import com.atsistemas.EncuestaProj.service.UserService;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDAO resultDAO;
	
	@Autowired
	SurveyService surveyService;
	
	@Autowired
	UserService userService;
	
	
	@Override
	public QuestionAnswerDTO createUserQuestionSurvey(Integer idUser, Integer idSurvey) throws NotFoundException {
		// TODO Auto-generated method stub
		User user =userService.findById(idUser);
		Survey survey = surveyService.findById(idSurvey);
		List<Question> questionSurvey= survey.getPreguntas().stream().collect(Collectors.toList());
		
	}

	@Override
	public void ResponseUserQuestionSurvey(Integer idUser, Integer idSurvey, Integer idQuestion, Integer idAnswer) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<QuestionAnswerDTO> createUserAllQuestionSurvey(Integer idUser, Integer idSurvey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultCourseDTO getResultUserCourse(Integer idUser, Integer idCourse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSurveyDTO getResultUserSurvey(Integer idUser, Integer idSurvey) {
		// TODO Auto-generated method stub
		return null;
	}

}
