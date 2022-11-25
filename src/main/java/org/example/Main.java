package org.example;

import model.Course;
import model.Professor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

        EntityManager manager = factory.createEntityManager();

        Course course1 = new Course("MAP", 6);
        Course course2 = new Course("DB", 6);
        Professor prof = new Professor("john", Arrays.asList(course1, course2));

        manager.getTransaction().begin();

        Query nativeQuery = manager.createNativeQuery("select id, ects, name from course where name=:course_name", Course.class);
        nativeQuery.setParameter("course_name", "DB");
        Course c = (Course) nativeQuery.getSingleResult();

        System.out.println(c.toString());
//        manager.persist(prof);

        manager.getTransaction().commit();
    }
}