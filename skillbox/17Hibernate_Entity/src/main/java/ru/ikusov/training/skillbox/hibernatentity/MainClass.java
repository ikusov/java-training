package ru.ikusov.training.skillbox.hibernatentity;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ru.ikusov.training.skillbox.hibernatentity.model.Course;
import ru.ikusov.training.skillbox.hibernatentity.model.Teacher;

import javax.persistence.criteria.CriteriaBuilder;

import static ru.ikusov.training.utils.Console.p;

public class MainClass {
    private final static SessionFactory theVeryOneAndTheVeryOnlySessionFactory;

    static {
        theVeryOneAndTheVeryOnlySessionFactory = letsCreateOurTheOnlyOneSessionFactoryAndNothingElseMatters();
    }

    public static final void main(String... lkjsfijwfalkjaglgdlfjj) {
        p("Hello world.");
//        askUserToEnterCourseNameOrIdToPrintStudentsCount();
//        listCoursesList();
        askUserForTeacherNameOrIdToPrintThatTeachersCoursesAndStudents();
    }

    //various main methods
    //just lists courses list
    public static final void listCoursesList() {
        //try-with-resources will autoclose session after tryblock execute completion
        try (Session session = getSession()) {
            ((List<Course>)(session.createQuery("from Course").list())).forEach(course -> p(course.toExtendedString()));
        }
    }

    //various main methods
    //asks user to enter course name or id to print students count
    public static final void askUserToEnterCourseNameOrIdToPrintStudentsCount() {
        Scanner scanner = new Scanner(System.in);

        p("The Application will print students on selected course.");p("Please enter course name or id:");
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
                p(getCourseByName(session, courseName).toExtendedString());
            } catch (IndexOutOfBoundsException e) {
                p("Bad command or course name!");
            } else try {
                p(getCourseById(session, courseId).toExtendedString());
            } catch (IndexOutOfBoundsException e) {
                p("Bad command or course id!");
            }
        }
    }

    //various main methods
    //asks user for teacher name or id to print that teacher's courses and students
    public static final void askUserForTeacherNameOrIdToPrintThatTeachersCoursesAndStudents() {
        Scanner scanner = new Scanner(System.in);

        p("Application lists courses and students of a teacher.");p("Enter teacher name or id: ", "");
        String input = scanner.nextLine();
        try (Session session = getSession()) {
            try {
                p(getTeacherById(session, Integer.parseInt(input)).toExtendedString());
            } catch (NumberFormatException e) {
                try {
                    p(getTeacherByName(session, input).toExtendedString());
                } catch (IndexOutOfBoundsException e1) {
                    p("Teacher name not found!");
                }
            } catch (IndexOutOfBoundsException e) {
                p("Teacher id not found!");
            }
        }
    }


    //auxiliary method
    //will return first found course with specified name
    //theoretically there may be some courses with the same name in the database
    //but it is stupid to do this for database developer
    private static Course getCourseByName (Session session, String name) {
        return ((List<Course>)(session.createQuery("from Course").list())).stream()
                .filter(course -> course.getName().equals(name))
                .toList().get(0);
    }

    //auxiliary method
    //will return first found course with specified ID
    //such course is the only because course ID is the primary key
    private static Course getCourseById (Session session, Integer id) {
        return ((List<Course>)(session.createQuery("from Course").list())).stream()
                .filter(course -> course.getId() == id)
                .toList().get(0);
    }

    //auxiliary method
    //will return first found teacher with specified ID
    //such teacher is the only be cause of teacher ID being primary key
    private static Teacher getTeacherById (Session session, Integer id) {
        return ((List<Teacher>)(session.createQuery("from Teacher").list())).stream()
                .filter(teacher -> teacher.getId() == id)
                .toList().get(0);
    }

    //auxiliary method
    //will return first found teacher with specified Name
    //theoretically there may be several teachers with the same name in the table
    //but i don't know what to do with the problem
    private static Teacher getTeacherByName(Session session, String name) {
        return ((List<Teacher>)(session.createQuery("from Teacher").list())).stream()
                .filter(teacher -> teacher.getName().equals(name))
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
