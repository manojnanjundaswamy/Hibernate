package com.hibernate.main;

import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class FetchJoinMain {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			
			session.beginTransaction();		
			Query<Instructor> query = session.createQuery("SELECT i FROM Instructor i "
														+ "JOIN FETCH i.courses "
														+ "WHERE i.id=:theInstructorId", Instructor.class);			
			query.setParameter("theInstructorId", 1L);
			
			Instructor tempInstructor = query.getSingleResult();
			System.out.println("Instructor: "+tempInstructor.getFirstName());
			System.out.println("Courses: "+tempInstructor.getCourses().stream()
								.map(course -> course.getTitle()).collect(Collectors.joining(", ")));
			
			session.getTransaction().commit();
			
			session.close();
			System.out.println("After session closing");
			System.out.println("Courses: "+tempInstructor.getCourses().stream()
					.map(course -> course.getTitle()).collect(Collectors.joining(", ")));
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}
}
