package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

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
//			Student tempStudent = new Student("Conrad", "Doe", "conradbuys11@gmail.com");
//			
//			//start transaction
//			session.beginTransaction();
//			
//			//save student obj
//			session.save(tempStudent);
//			
//			//commit transaction
//			session.getTransaction().commit();
			
			//let's do some query-ing!
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			displayStudents(theStudents);
			
			//find students with more specific criteria
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			displayStudents(theStudents);
			
			//even more specific criteria!
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe' OR s.firstName = 'Daffy'").getResultList();
			displayStudents(theStudents);
			
			//using a LIKE criteria
			//% is wild card, means thing ends with luv2code.com
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
			displayStudents(theStudents);
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student student : theStudents) {
			System.out.println(student);
		}
	}

}