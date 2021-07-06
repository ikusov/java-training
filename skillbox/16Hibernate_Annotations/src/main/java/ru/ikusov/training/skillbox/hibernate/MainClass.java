package ru.ikusov.training.skillbox.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.ikusov.training.utils.Console;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Scanner;

import static ru.ikusov.training.utils.Console.p;


public class MainClass {
    private final static SessionFactory theVeryOneAndTheVeryOnlySessionFactory;

    static {
        theVeryOneAndTheVeryOnlySessionFactory = letsCreateOurTheOnlyOneSessionFactoryAndNothingElseMatters();
    }

    public static final void main(String... lkjsfijwfalkjaglgdlfjj) {
//        p("Hello world.");
//        test1();
        test2();
    }

    //just lists courses list
    private static final void test1() {
        try (Session session = getSession()) {
            session.createQuery("from Course").list().forEach(Console::p);
        }
    }

    //asks user to enter course name or id to print students count
    private static final void test2() {
        Scanner scanner = new Scanner(System.in);

        p("The Application will print students count on selected course.");p("Please enter course name or id:");
        String inputString = scanner.nextLine(),
                courseName = "";
        Integer courseId = 0;

        try {
            courseId = Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            courseName = inputString;
        }

        //try-with-resources will autoclose session after work
        try (Session session = getSession()) {
            if (!courseName.equals(""))try {
                p(getCourseByName(session, courseName));
            } catch (IndexOutOfBoundsException e) {
                p("Bad command or course name!");
            } else try {
                p(getCourseById(session, courseId));
            } catch (IndexOutOfBoundsException e) {
                p("Bad command or course id!");
            }
        }
    }

    //will return first found course with specified name
    //theoretically there may be some courses with the same name in the database
    //but it is stupid to do this for database developer
    private static Course getCourseByName (Session session, String name) {
        return ((List<Course>)(session.createQuery("from Course").list())).stream()
                .filter(course -> course.getName().equals(name))
                .toList().get(0);
    }

    //will return first found course with specified ID
    //such course is the only because course ID is the primary key
    private static Course getCourseById (Session session, Integer id) {
        return ((List<Course>)(session.createQuery("from Course").list())).stream()
                .filter(course -> course.getId() == id)
                .toList().get(0);
    }


    //session and session factory getters for code readability

    private static Session getSession() {
        return theVeryOneAndTheVeryOnlySessionFactory.openSession();
    }

    //more toothcrushing method names for the God of Hibernate ORM!!11
    private static SessionFactory letsCreateOurTheOnlyOneSessionFactoryAndNothingElseMatters() {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}
