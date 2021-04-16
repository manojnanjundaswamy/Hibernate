package com.hibernate.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class CreateCoursesMain {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			
			session.beginTransaction();
			Instructor tempInstructor = session.get(Instructor.class, 1L);
			
			List<Course> tempCourses = new ArrayList<>();
			tempCourses.add(new Course("Advanced Mathemetics"));
			tempCourses.add(new Course("Spring-boot MasterClass"));
			
			tempInstructor.addCourse(tempCourses.get(0));
			tempInstructor.addCourse(tempCourses.get(1));
			
			for(Course tempCourse : tempCourses){
				session.save(tempCourse);
			}			
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}
}
