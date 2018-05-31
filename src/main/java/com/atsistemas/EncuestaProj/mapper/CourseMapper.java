package com.atsistemas.EncuestaProj.mapper;

import java.util.List;


import com.atsistemas.EncuestaProj.dto.CourseDTO;
import com.atsistemas.EncuestaProj.dto.CourseDTOPost;
import com.atsistemas.EncuestaProj.model.Course;

public interface CourseMapper {

	public Course courseDtoToDao(CourseDTO courseDTO);
	public CourseDTOPost  courseDaoToDto(Course course);
	public List<Course> courseGetDtoToDao(List<CourseDTO> courseDTO);
	public List<CourseDTOPost> courseGetDaoToDto(List<Course> courses);
	
}
