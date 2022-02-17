package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create student obj
			Student tempStudent = new Student("Conrad", "Buys", "conradbuys11@gmail.com");
			
			//start transaction
			session.beginTransaction();
			
			//save student obj
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
