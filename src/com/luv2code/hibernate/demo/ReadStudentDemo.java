package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			Student tempStudent = new Student("Daffy", "Duck", "daffyduck@luv2code.com");
			
			//start transaction
			session.beginTransaction();
			
			//save student obj
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			//read daffy duck based on its primary key
			session = factory.getCurrentSession();
			session.beginTransaction();
			int id = tempStudent.getId();
			Student student = session.get(Student.class, id);
			System.out.println("Read student! " + student);
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
