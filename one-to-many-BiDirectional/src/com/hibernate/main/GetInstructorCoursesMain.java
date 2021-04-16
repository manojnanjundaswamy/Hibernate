package com.hibernate.main;

import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class GetInstructorCoursesMain {

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
			System.out.println("Instructor: "+tempInstructor.getFirstName());
			System.out.println("Courses: "+tempInstructor.getCourses().stream()
								.map(course -> course.getTitle()).collect(Collectors.joining(", ")));
			
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}
}
