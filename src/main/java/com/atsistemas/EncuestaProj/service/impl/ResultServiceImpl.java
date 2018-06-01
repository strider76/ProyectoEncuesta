package com.atsistemas.EncuestaProj.service.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.ResultDAO;
import com.atsistemas.EncuestaProj.dto.AnswerResDTO;
import com.atsistemas.EncuestaProj.dto.QuestionResAllDTO;
import com.atsistemas.EncuestaProj.dto.QuestionResDTO;
import com.atsistemas.EncuestaProj.dto.ResultCourseDTO;
import com.atsistemas.EncuestaProj.dto.ResultSurveyDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.ResultNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyFinishedException;
import com.atsistemas.EncuestaProj.model.Answer;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Result;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.User;
import com.atsistemas.EncuestaProj.service.AnswerService;
import com.atsistemas.EncuestaProj.service.CourseService;
import com.atsistemas.EncuestaProj.service.QuestionService;
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
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	CourseService courseService;
	
	
	@Override
	public QuestionResDTO createUserSurveyQuestion(Integer idUser, Integer idSurvey) throws NotFoundException, SurveyFinishedException {

		User user =userService.findById(idUser);
		Survey survey = surveyService.findById(idSurvey);

		List<Question> questionSurvey= survey.getPreguntas();
		questionSurvey.removeAll(resultDAO.findQuestionbyUserAndCuestionario(user, survey));
		
		if (questionSurvey.size()>0) {
			Collections.shuffle(questionSurvey);
			Result result = new Result();
			result.setUser(user);
			result.setCuestionario(survey);
			result.setCourse(survey.getCourse());
			result.setCreationDate(new Date());
			result.setQuestion(questionSurvey.get(0));
			result.setQuestionName(questionSurvey.get(0).getName());
			result = resultDAO.save(result);
			
			QuestionResDTO questionsAnswer = new QuestionResDTO();
			questionsAnswer.setIdQuestion(result.getIdResult());
			questionsAnswer.setPregunta(questionSurvey.get(0).getName());
			for (Answer answer : questionSurvey.get(0).getAnswers())
				questionsAnswer.getRespuestas().add(new AnswerResDTO(answer.getIdResultado(),answer.getRespuesta()));
			return questionsAnswer;
			
			
		} else
			throw new SurveyFinishedException("El Survey "+ idSurvey +" del User " + idUser +" ha sido completado");
		
	}

	@Override
	public void ResponseUserQuestionSurvey(Integer idQuestionRes, Integer idAnswer) throws NotFoundException {
		
		Optional<Result> resultSearch = resultDAO.findById(idQuestionRes);
		if (resultSearch.isPresent()) {
			Result result = resultSearch.get();
			Long inicioPregunta = result.getCreationDate().getTime();
			Long respuestaPregunta = new Date().getTime();
			//La respuesta no puede ser superior a 1 minuto
			if (respuestaPregunta-inicioPregunta<60000) {
				Answer answer = answerService.findById(idAnswer);
				result.setAnswer(answer.getRespuesta());
				result.setEsCorrecto(answer.getEsCorrecta());
			} else {
				result.setAnswer("tiempo excedido");
				result.setEsCorrecto(false);
			}
			resultDAO.save(result);
		} else
			throw new ResultNotFoundException("Result no encontrado idResult('"+ idQuestionRes +"')");

	}

	@Override
	public List<QuestionResDTO> createUserAllQuestionSurvey(Integer idUser, Integer idSurvey) throws NotFoundException {
		User user = userService.findById(idUser);
		Survey survey = surveyService.findById(idSurvey);
		List<Question> questionSurvey = survey.getPreguntas();
		List<QuestionResDTO> questionList = new ArrayList<>();
		for (Question question : questionSurvey) {
			Result result = new Result();
			result.setUser(user);
			result.setCuestionario(survey);
			result.setCourse(survey.getCourse());
			result.setQuestion(question);
			result.setQuestionName(question.getName());
			result.setCreationDate(new Date());
			result = resultDAO.save(result);
			
			QuestionResDTO questionRes = new QuestionResDTO();
			questionRes.setIdQuestion(result.getIdResult());
			questionRes.setPregunta(result.getQuestionName());
			for (Answer answer : question.getAnswers())
				questionRes.getRespuestas().add(new AnswerResDTO(answer.getIdResultado(), answer.getRespuesta()));
			questionList.add(questionRes);
			
		}
		return questionList;
		
	}

	@Override
	public void ResponseUserAllQuestionSurvey(Integer idUser, Integer idSurvey, List<QuestionResAllDTO> questionResAllDto) throws NotFoundException {
		User user = userService.findById(idUser);
		Survey survey = surveyService.findById(idSurvey);
		List<Result> resultList = resultDAO.findAllByUserAndCuestionario(user, survey);
		for (Result result : resultList){
			Boolean encontrado = false;
			for(QuestionResAllDTO questionRes : questionResAllDto){
				if (questionRes.getIdResult()==result.getIdResult()){
					Answer answer = answerService.findById(questionRes.getIdAnswer());
					result.setAnswer(answer.getRespuesta());
					result.setEsCorrecto(answer.getEsCorrecta());
					encontrado=true;
					break;
				}
			}
			if (!encontrado) {
				result.setAnswer("sin contestar");
				result.setEsCorrecto(false);
			}
			resultDAO.save(result);
		}
	}	
	
	@Override
	public List<ResultCourseDTO> getResultUserCourse(Integer idUser, Integer idCourse) throws NotFoundException {
		User user = userService.findById(idUser);
		List<ResultCourseDTO> resultSurveysCourse = new ArrayList<>();
		List<Survey> surveyList = courseService.findById(idCourse).getCuestionarios();
		for (Survey survey : surveyList) {
			ResultCourseDTO resultSurvey = new ResultCourseDTO();
			resultSurvey.setIdSurvey(survey.getIdCuestionario());
			resultSurvey.setSurveyName(survey.getIdentificador());
			resultSurvey.setNota(resultDAO.countByUserAndCuestionarioAndEsCorrectoTrue(user, survey)/survey.getPreguntas().size());
			resultSurveysCourse.add(resultSurvey);
		}
		return resultSurveysCourse;
		
	}

	@Override
	public List<ResultSurveyDTO> getResultUserSurvey(Integer idUser, Integer idSurvey) throws NotFoundException {
		User user = userService.findById(idUser);
		Survey survey = surveyService.findById(idSurvey);
		List<ResultSurveyDTO> resultList = new ArrayList<>();
		for (Result result : resultDAO.findAllByUserAndCuestionario(user, survey))
			resultList.add(new ResultSurveyDTO(result.getQuestionName(), result.getAnswer(), (result.getEsCorrecto()?"Correcto":"Incorrecto")));
		return resultList;
		
	}



}
