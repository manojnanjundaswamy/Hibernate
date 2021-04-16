package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.Review;
import com.hibernate.entity.Student;

public class CreateCourseAndStudentMain {

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

			Course tempCourse = new Course("Full stack web development");
			System.out.println("\nSaving the course...");
			session.save(tempCourse);
			System.out.println("\nSaved Course: "+tempCourse);
			
			Student tempStudent1 = new Student("Manoj", "Nanjundaswamy", "manojnanjundaswamy@gmail.com");
			Student tempStudent2 = new Student("Ramesh", "Vasanth", "ramesh@gmail.com");
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			System.out.println("\nSaving the Student...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			
			System.out.println("\nSaved Students: "+tempCourse.getStudents());
			
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}
}
