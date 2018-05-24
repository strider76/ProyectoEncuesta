package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.HashSet;
import java.util.Set;

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
	public Set<Course> courseGetDtoToDao(Set<CourseDTO> courseDTO) {
		Set<Course> courseList = new HashSet<>();
		for (CourseDTO course : courseDTO) 
			courseList.add(courseDtoToDao(course));
		return courseList;
	}

	@Override
	public Set<CourseDTOPost> courseGetDaoToDto(Set<Course> courses) {
		Set<CourseDTOPost> courseList = new HashSet<>();
		for (Course curCourse : courses) 
			courseList.add(courseDaoToDto(curCourse));
		return courseList;
	}

}
