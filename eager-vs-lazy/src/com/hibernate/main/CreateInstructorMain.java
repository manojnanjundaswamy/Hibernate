package com.hibernate.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class CreateInstructorMain {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			List<Instructor> instructorList = new ArrayList<>();
			instructorList.add(new Instructor("Manoj", "Nanjundaswamy", "mnmanu11@gmail.com"));
			instructorList.add(new Instructor("Ravi", "Kiran", "ravikiran@gmail.com"));
			instructorList.add(new Instructor("Charan", "Raj", "charan@gmail.com"));
			
			List<InstructorDetail> instructorDetailList = new ArrayList<>();
			instructorDetailList.add(new InstructorDetail("https://youtube.com/manojnanjundaswamy", "Coding, Running"));
			instructorDetailList.add(new InstructorDetail("https://youtube.com/ravikiran", "coding"));
			instructorDetailList.add(new InstructorDetail("https://youtube.com/charnraj", "Guitar"));

			for(int i=0; i<instructorList.size(); i++) {
				instructorList.get(i).setInstructorDeatil(instructorDetailList.get(i));
			}
			
			session.beginTransaction();
			for(Instructor tempInstructor : instructorList) {
				session.save(tempInstructor);					
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
