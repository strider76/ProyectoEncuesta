package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.dto.CourseDTOPost;
import com.atsistemas.EncuestaProj.mapper.CourseMapper;
import com.atsistemas.EncuestaProj.model.Course;

@Component
public class CourseMapperImpl implements CourseMapper {

	@Autowired
	DozerBeanMapper courseMapper;
	
	@Override
	public Course courseDtoToDao(CourseDTO courseDTO) {
		return courseMapper.map(courseDTO, Course.class);
	}

	@Override
	public CourseDTOPost courseDaoToDto(Course course) {
		return courseMapper.map(course, CourseDTOPost.class);
	}

	@Override
	public List<Course> courseGetDtoToDao(List<CourseDTO> courseDTO) {
		List<Course> courseList = new ArrayList<>();
		for (CourseDTO course : courseDTO) 
			courseList.add(courseDtoToDao(course));
		return courseList;
	}

	@Override
	public List<CourseDTOPost> courseGetDaoToDto(List<Course> courses) {
		List<CourseDTOPost> courseList = new ArrayList<>();
		for (Course curCourse : courses) 
			courseList.add(courseDaoToDto(curCourse));
		return courseList;
	}

}
