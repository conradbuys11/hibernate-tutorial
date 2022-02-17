package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create 3 student objs
			Student tempStudent1 = new Student("Super", "Duper", "super@duper.com");
			Student tempStudent2 = new Student("John", "Doe", "john.doe@luv2code.com");
			Student tempStudent3 = new Student("Student", "Name", "definitelyanemailaddress@com.org");
			
			//start transaction
			session.beginTransaction();
			
			//save student obj
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
