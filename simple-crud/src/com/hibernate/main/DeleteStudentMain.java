package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class DeleteStudentMain {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			session.createQuery("delete Student a where a.id=2").executeUpdate();
		
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			factory.close();
		}
	}
}
