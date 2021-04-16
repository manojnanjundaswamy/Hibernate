package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class DeleteInstructorDetailMain {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, 1L);
			
			System.out.println("Instructor Detail: "+tempInstructorDetail);
			
			System.out.println("Associated Instructor: "+tempInstructorDetail.getInstructor());
			
			//Delete Instructor Detail
			
			session.delete(tempInstructorDetail);

			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}
}
