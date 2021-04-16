	package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.Review;
import com.hibernate.entity.Student;

public class GetCoursesForManojMain {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			
			session.beginTransaction();

			Student tempStudent = session.get(Student.class, 1L);
			
			System.out.println("Loaded Student: "+tempStudent);
			System.out.println("Courses: "+tempStudent.getCourses());

			Course tempCourse1 = new Course("Sql Basics");
			Course tempCourse2 = new Course("HTML and CSS Master Class");
			
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			System.out.println("Saving Courses...");
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}
}
