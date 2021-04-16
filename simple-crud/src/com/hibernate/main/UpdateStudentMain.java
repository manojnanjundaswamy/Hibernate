package com.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class UpdateStudentMain {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Student myStudent = session.get(Student.class, 1);
			myStudent.setEmail("mnmanu11@gmail.com");
		
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> allStudents) {
		for(Student tempStudent: allStudents) {
			System.out.println(tempStudent);
		}
	}
}
