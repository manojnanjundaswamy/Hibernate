package com.hibernate.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class CreateStudentMain {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			List<Student> newStudents = new ArrayList<>();
			Student newStudent1 = new Student("Manoj", "Nanjundaswamy", "manojnanjundaswamy@gmail.com");
			newStudents.add(newStudent1);
			newStudents.add(new Student("Ravi", "Kiran", "raviiran@mail.com"));
			newStudents.add(new Student("Anu", "deep", "anudeep@mail.com"));
			System.out.println("Saving student..");
			session.beginTransaction();
			for(Student newStud : newStudents) {
				session.save(newStud);
			}
			//session.save(newStudent1);
			session.getTransaction().commit();
			System.out.print("Students saved");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			factory.close();
		}
	}
}
