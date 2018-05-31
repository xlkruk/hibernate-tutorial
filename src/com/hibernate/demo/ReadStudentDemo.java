package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// create session factory

				SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class).buildSessionFactory();
				// create the session
				Session session = sessionFactory.getCurrentSession();

				try {
					// use the session object to save the Entity
					// create the object
					System.out.println("Creating new Student object...");
					Student student = new Student("John", "Doe", "john.doe@wp.pl");

					// begin transaction
					session.beginTransaction();
					// save the object
					System.out.println("Saving...");
					session.save(student);
					// commit the transaction
					session.getTransaction().commit();

					// read the obj
					System.out.println("gen student:" + student.getId());
					session = sessionFactory.getCurrentSession();
					session.beginTransaction();

					Student myStudent = session.get(Student.class, student.getId());
					System.out.println(myStudent);
					session.getTransaction().commit();
					System.out.println("Done!");
				} catch (Exception e) {
					// close the connection
					session.close();
				}


	}

}
