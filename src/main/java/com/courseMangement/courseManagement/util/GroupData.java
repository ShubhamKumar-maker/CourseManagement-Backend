package com.courseMangement.courseManagement.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.courseMangement.courseManagement.models.Course;

public class GroupData {
	
	public static Map<String,Long>  groupbyfields(List<Course>course,String findbycolumn )
	{
		Map<String,Long>mp=new HashMap<>();
		
		if(findbycolumn.equals("location")) {
		mp=course.stream().collect(Collectors.groupingBy(Course::getLocation,Collectors.counting()));
				System.out.println(mp);	
				return mp;
		}
		else if(findbycolumn.equals("description"))
		{
			mp=course.stream().collect(Collectors.groupingBy(Course::getDescription,Collectors.counting()));
			System.out.println(mp);	
			return mp;
			
		}
		return mp;
	}

}
