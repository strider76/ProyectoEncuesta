package com.atsistemas.EncuestaProj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.QuestionResAllDTO;
import com.atsistemas.EncuestaProj.dto.QuestionResDTO;
import com.atsistemas.EncuestaProj.dto.ResultCourseDTO;
import com.atsistemas.EncuestaProj.dto.ResultSurveyDTO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyFinishedException;
import com.atsistemas.EncuestaProj.service.ResultService;

@RestController
@RequestMapping("/result")
public class ResultController {

	@Autowired
	ResultService resultService;
	
	@GetMapping("/user/{idUser}/survey/{idSurvey}/question")
	public QuestionResDTO getQuestionSurvey(@PathVariable Integer idUser, @PathVariable Integer idSurvey) throws NotFoundException, SurveyFinishedException {
		return resultService.createUserSurveyQuestion(idUser,idSurvey);
	}
	
	@GetMapping("/user/{idUser}/survey/{idSurvey}/all")
	public List<QuestionResDTO> getAllQuestionSurvey(@PathVariable Integer idUser, @PathVariable Integer idSurvey) throws NotFoundException {
		return resultService.createUserAllQuestionSurvey(idUser,idSurvey);
	}

	@PutMapping("/user/{idUser}/survey/{idSurvey}/all")
	public void SetAllQuestionSurvey(@PathVariable Integer idUser, 
													 @PathVariable Integer idSurvey,
													 @RequestBody List<QuestionResAllDTO> questionResAllDto) throws NotFoundException {
		resultService.ResponseUserAllQuestionSurvey(idUser,idSurvey,questionResAllDto);
	}	
	
	@PutMapping("/{idQuestionRes}/answer/{idAnswer}")
	public void SetAnswerQuestionSurvey(@PathVariable Integer idQuestionRes,
										@PathVariable Integer idAnswer) throws NotFoundException {
		resultService.ResponseUserQuestionSurvey(idQuestionRes,idAnswer);
	}
	
	
	
	@GetMapping("/user/{idUser}/course/{idCourse}")
	public List<ResultCourseDTO> getResultUserCourse(@PathVariable Integer idUser, @PathVariable Integer idCourse) throws NotFoundException {
		return resultService.getResultUserCourse(idUser,idCourse);
	}
	
	@GetMapping("/user/{idUser}/survey/{idSurvey}")
	public List<ResultSurveyDTO> getResultUserSurvey(@PathVariable Integer idUser, @PathVariable Integer idSurvey) throws NotFoundException {
		return resultService.getResultUserSurvey(idUser,idSurvey);
	}
	
	
	
}
