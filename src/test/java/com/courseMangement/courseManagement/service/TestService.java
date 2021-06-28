package com.courseMangement.courseManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockitoSession;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.courseMangement.courseManagement.entities.CourseEntity;
import com.courseMangement.courseManagement.entities.Creator;
import com.courseMangement.courseManagement.entities.Skill;
import com.courseMangement.courseManagement.models.Course;
import com.courseMangement.courseManagement.repository.CourseRepository;
import com.courseMangement.courseManagement.util.Mapper;
import com.courseMangement.courseManagement.utils.SetData;

@SpringBootTest
public class TestService {
	
	@Mock
	private CourseRepository courseRepo;
	
	@InjectMocks
	private CourseService courseservice;
	
	@Test
	public void TestgetCourse()
	{
		List<CourseEntity>ltcourseEntity=new ArrayList<>();
		ltcourseEntity.add(SetData.setcourseEntitydata());
		List<Course>course=new ArrayList<>();
		Mockito.when(courseRepo.findAll()).thenReturn(ltcourseEntity);
		course=courseservice.getCourse();
		 assertNotNull(course);	
	}
	
	@Test
	public void testgetbyId()
	{
		Mockito.when(courseRepo.findById(100L)).thenReturn(SetData.setcourseEntitydata2());
		Course course=courseservice.getCourseById(100L);
		assertNotNull(course);
		assertEquals(course.getLocation(),"Banglore");
	}
	
	@Test
	public void getByLocation()
	{
		List<CourseEntity>ltcourseEntity=new ArrayList<>();
		ltcourseEntity.add(SetData.setcourseEntitydata());
		List<Course>course=new ArrayList<>();
		Mockito.when(courseRepo.findByLocation("Banglore")).thenReturn(ltcourseEntity);
		course=courseservice.getCourse();
		 assertNotNull(course);	
		
	}
	
	
}
