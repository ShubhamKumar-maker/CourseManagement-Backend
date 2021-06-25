package com.courseMangement.courseManagement.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.courseMangement.courseManagement.entities.CourseEntity;
import com.courseMangement.courseManagement.models.Course;
import com.courseMangement.courseManagement.repository.CourseRepository;
import com.courseMangement.courseManagement.repository.CreatorRepository;
import com.courseMangement.courseManagement.repository.SkillRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courserepository;
	@Autowired
	private SkillRepository skillrepository;
	@Autowired
	private CreatorRepository creatorrepository;
	
	@Autowired
    private JavaMailSender mailSender;
	
	

	public void saveCourse() {

	}

	public List<Course> getCourse() {
		//sendMail("test","test mail");
		//System.out.println(getparticipantsData());
		List<Course> lt = new ArrayList<>();
		courserepository.findAll().forEach(s -> {
			Course c = new Course();
			c.setId(s.getId());
			c.setPrerequesite(s.getPrerequesite());
			c.setDescription(s.getDescription());
			c.setFeedback(s.getFeedback());
			c.setLastupdated(s.getLastupdated());
			c.setLocation(s.getLocation());
			c.setSkill(s.getSkill());
			c.setCreator(s.getCreator());
			lt.add(c);
		});
			
		return lt;

	}
	
	public Course getCourseById(long id)
	{
			if(courserepository.findById(id).isPresent())
			{
				Optional<CourseEntity>entity=courserepository.findById(id);
				return mapEntity(entity.get());
				
			}
		return null;		
	}
	
	public List<Course>getCourseByLocation(String location)
	{
		List<Course>lt=new ArrayList<>();
		 courserepository.findByLocation(location).forEach(l->{			 
			 lt.add(mapEntity(l)); 
		 });
		 return lt;
	}

	
	public Course addNewCourses(Course course) {
		CourseEntity entity=courserepository.save(mapObject(course));
		return mapEntity(entity);
	}

	public Course updateCourse(Course course, long id) {
		if(!courserepository.findById(id).isEmpty())
		{
			CourseEntity entity=courserepository.save(mapObject(course));
			return mapEntity(entity);
		}
		return null;
	}

	public int deleteCourse(long id) {
		if(!courserepository.findById(id).isEmpty())
		{
			courserepository.deleteById(id);
			return 1;
		}
		 return 0;
	}
	public CourseEntity mapObject(Course course)
	{
		CourseEntity entity=new CourseEntity();
		entity.setId(course.getId());
		entity.setDescription(course.getDescription());
		entity.setFeedback(course.getFeedback());
		entity.setLocation(course.getLocation());
		entity.setPrerequesite(course.getPrerequesite());
		entity.setLastupdated(new Date());
		entity.setSkill(course.getSkill());
		entity.setCreator(course.getCreator());
		return entity;
		
	}
	
	public Course mapEntity(CourseEntity courseEntity)
	{
		Course object =new Course();
		object.setId(courseEntity.getId());
		object.setDescription(courseEntity.getDescription());
		object.setFeedback(courseEntity.getFeedback());
		object.setLastupdated(courseEntity.getLastupdated());
		object.setLocation(courseEntity.getLocation());
		object.setSkill(courseEntity.getSkill());
		object.setCreator(courseEntity.getCreator());
		object.setPrerequesite(courseEntity.getPrerequesite());
		return object;
	}

	
	 public void sendMail(String subject, String body) 
	    {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo("sk709212@gmail.com");
	        message.setFrom("sk7.rocky17@gmail.com");
	        message.setSubject(subject);
	        message.setText(body);
	        mailSender.send(message);
	        System.out.println("mail send");
	    }
	 public String getparticipantsData()
	 {
		 RestTemplate resttemplate=new RestTemplate();
		 String baseURL="http://localhost:8901/testParticipants/getparticipantsEmail";
		 ResponseEntity<String> response=null;
		 response=resttemplate.exchange(baseURL, HttpMethod.GET,getHeaders(),String.class);
		 String participantsEmail=response.getBody().toString();
		 return participantsEmail.substring(1,participantsEmail.length()-1);
	 }

	private static HttpEntity getHeaders() {
		HttpHeaders headers=new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
