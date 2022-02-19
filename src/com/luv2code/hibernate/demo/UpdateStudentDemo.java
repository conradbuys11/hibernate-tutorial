package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			int id = 1;
			Student student = session.get(Student.class, id);
			student.setFirstName("Scooby");
			//no need to have any session.update or anything like that
			//it will automatically update since we got from session
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
