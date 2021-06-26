package ru.ikusov.training.skillbox.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

import static ru.ikusov.training.utils.Console.p;


public class MainClass {
    private static SessionFactory sessionFactory;

    public static void main(String... lkjsdfoijwefoijdngsoigjklaoij) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        test2();
    }

    //output all courses from courses table
    public static void test1() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Course> courses = session.createQuery("FROM Course").list();
        transaction.commit();
        session.close();

        courses.forEach(course ->
                p(String.format("Course name: %s, course type: %s, course duration: %d, " +
                                "course students count: %d",
                                course.getName(), course.getType(), course.getDuration(),
                                course.getStudentsCount())));
    }

    //output students count of user-entered course
    public static void test2() {
        Scanner sc = new Scanner(System.in);
        p("Enter course name or ID to know students count: ");
        String entered = sc.nextLine();
        try {
            Integer courseId = Integer.parseInt(entered);
            getStudentsCount(courseId);
        } catch (NumberFormatException e) {
            getStudentsCount(entered);
        }
    }

    public static void getStudentsCount(String courseName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Course> courseList;

            Query query = session.createQuery("from Course where name=:paramName");
            query.setParameter("paramName", courseName);
            courseList = query.list();

            transaction.commit();
            session.close();

        if (!courseList.isEmpty()) {
            courseList.forEach(course ->
                    p(String.format("Course name: %s, students count: %d",
                            course.getName(), course.getStudentsCount())));
        } else {
            p("Course name not found: " + courseName);
        }
    }

    public static void getStudentsCount(Integer courseId) {
        List<Course> courseList;

        String queryString = "from Course where id=:courseId";
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(queryString);
        query.setParameter("courseId", courseId);

        courseList = query.list();

        transaction.commit();
        session.close();

        if (!courseList.isEmpty()) {
            courseList.forEach(course -> p(String.format("Course name: %s, students count: %d",
                                            course.getName(), course.getStudentsCount())));
        } else p("Course not found: " + courseId);
    }

}
