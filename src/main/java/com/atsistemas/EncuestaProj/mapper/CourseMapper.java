package com.atsistemas.EncuestaProj.mapper;

import java.util.Set;

import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.dto.CourseDTOPost;
import com.atsistemas.EncuestaProj.model.Course;

public interface CourseMapper {

	public Course courseDtoToDao(CourseDTO courseDTO);
	public CourseDTOPost  courseDaoToDto(Course course);
	public Set<Course> courseGetDtoToDao(Set<CourseDTO> courseDTO);
	public Set<CourseDTOPost> courseGetDaoToDto(Set<Course> courses);
	
}
