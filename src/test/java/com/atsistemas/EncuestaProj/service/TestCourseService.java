package com.atsistemas.EncuestaProj.service;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.atsistemas.EncuestaProj.dao.CourseDAO;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.service.impl.CourseServiceImpl;



@RunWith(MockitoJUnitRunner.class)
public class TestCourseService {

	
	
	@InjectMocks
	CourseService courseService = new CourseServiceImpl();
	
	@Mock
	CourseDAO courseDAO;
	
	@Test
	public void testCreate() throws NotFoundException {
		
		Course course = new Course();
		course.setName("curso JPA");
		when(courseDAO.save(course)).thenReturn(new Course("curso JPA"));
		Course courseSave = courseService.create(course);
		Assert.assertEquals(course.getName(), courseSave.getName());
		
		
	}

}
