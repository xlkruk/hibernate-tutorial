package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		// create the session
		Session session = sessionFactory.getCurrentSession();

		try {
			// begin transaction
			session.beginTransaction();

			//query the students
			List<Student> students = session.createQuery("from Student").getResultList();
			printStudents(students);
			
			//query the students with lastName = kruk
			students = session.createQuery("from Student s where s.lastName='Kruk'").getResultList();
			
		
			System.out.println("Students with last name Kruk");
			printStudents(students);
			
			//query the students with lastName = kruk or first name john
			students = session.createQuery("from Student s where s.lastName='Kruk' or s.firstName='John' order by s.firstName asc").getResultList();
			System.out.println("Students with last name Kruk or first name John order by name");
			printStudents(students);
			
			//query the students with email like %wp.pl
			students = session.createQuery("from Student s where s.email like '%wp.pl'").getResultList();
			System.out.println("Students with email like %wp.pl");
			printStudents(students);
			
			
			session.getTransaction().commit();
			System.out.println("Done!");
		} catch (Exception e) {
			// close the connection
			session.close();
		}

	}

	private static void printStudents(List<Student> students) {
		for(Student student : students){
			System.out.println(student);
		}
	}

}
