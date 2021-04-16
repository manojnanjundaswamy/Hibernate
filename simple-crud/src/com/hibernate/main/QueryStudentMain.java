package com.hibernate.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class QueryStudentMain {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			
			session.beginTransaction();
			
			List<Student> allStudents = session.createQuery("select a from Student a").getResultList();
			
			displayStudents(allStudents);
			
			allStudents = session.createQuery("select a from Student a where LOWER(a.lastName)='nanjundaswamy'"
						+ "OR LOWER(a.lastName)='kiran'").getResultList();
			
			displayStudents(allStudents);
			
			
			
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
