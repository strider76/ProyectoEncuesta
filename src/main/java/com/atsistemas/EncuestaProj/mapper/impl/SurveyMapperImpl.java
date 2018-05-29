package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.EncuestaProj.dto.SurveyDTO;
import com.atsistemas.EncuestaProj.dto.SurveyDTOPost;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.mapper.SurveyMapper;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.service.CourseService;

@Component
public class SurveyMapperImpl implements SurveyMapper {

	@Autowired
	DozerBeanMapper surveyMapper;
	
	@Autowired
	CourseService courseService;
	
	@Override
	public Survey surveyDtoToDao(SurveyDTO survey) throws NotFoundException {
		Survey surveyRes = surveyMapper.map(survey, Survey.class);
		Course courseSearch = courseService.findById(survey.getIdCourse());
		surveyRes.setCourse(courseSearch);
		return surveyRes;
	}

	@Override
	public SurveyDTOPost surveyDaoToDto(Survey survey) {
		SurveyDTOPost surveyRes = surveyMapper.map(survey, SurveyDTOPost.class);
		surveyRes.setIdCourse(survey.getCourse().getIdCourse());
		return surveyRes;
	}

	@Override
	public Set<SurveyDTOPost> surveyGetDaoToDto(Set<Survey> surveys) {
		Set<SurveyDTOPost> surveyRes = new HashSet<>();
		for (Survey survey : surveys)
			surveyRes.add(surveyDaoToDto(survey));
		return surveyRes;
	}

	@Override
	public Set<Survey> surveyGetDtoToDao(Set<SurveyDTO> surveys) throws NotFoundException {
		Set<Survey> surveyRes = new HashSet<>();
		for (SurveyDTO survey : surveys)
			surveyRes.add(surveyDtoToDao(survey));
		return surveyRes;
	}

}
