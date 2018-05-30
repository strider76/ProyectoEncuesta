package com.atsistemas.EncuestaProj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.QuestionAnswerDTO;
import com.atsistemas.EncuestaProj.dto.ResultCourseDTO;
import com.atsistemas.EncuestaProj.dto.ResultSurveyDTO;
import com.atsistemas.EncuestaProj.service.ResultService;

@RestController
@RequestMapping("/result")
public class ResultController {

	@Autowired
	ResultService resultService;
	
	@GetMapping("/user/{idUser}/survey/{idSurvey}/question")
	public QuestionAnswerDTO getQuestionSurvey(@PathVariable Integer idUser, @PathVariable Integer idSurvey) {
		return resultService.createUserQuestionSurvey(idUser,idSurvey);
	}
	
	@GetMapping("/user/{idUser}/survey/{idSurvey}/all")
	public List<QuestionAnswerDTO> getAllQuestionSurvey(@PathVariable Integer idUser, @PathVariable Integer idSurvey) {
		return resultService.createUserAllQuestionSurvey(idUser,idSurvey);
	}
	
	@PostMapping("/user/{idUser}/survey/{idSurvey}/question/{idQuestion}/answer/{idAnswer}")
	public void SetAnswerQuestionSurvey(@PathVariable Integer idUser,
										@PathVariable Integer idSurvey,
										@PathVariable Integer idQuestion,
										@PathVariable Integer idAnswer) {
		resultService.ResponseUserQuestionSurvey(idUser,idSurvey,idQuestion,idAnswer);
	}
	
	@GetMapping("/user/{idUser}/course/{idCourse}")
	public ResultCourseDTO getResultUserCourse(@PathVariable Integer idUser, @PathVariable Integer idCourse) {
		return resultService.getResultUserCourse(idUser,idCourse);
	}
	
	@GetMapping("/user/{idUser}/survey/{idsurvey}")
	public ResultSurveyDTO getResultUserSurvey(@PathVariable Integer idUser, @PathVariable Integer idSurvey) {
		return resultService.getResultUserSurvey(idUser,idSurvey);
	}
	
	
	
}
