package ru.ikusov.training.skillbox.hibernatehql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.ikusov.training.skillbox.hibernatehql.model.Course;
import ru.ikusov.training.skillbox.hibernatehql.model.LinkedPurchase;
import ru.ikusov.training.skillbox.hibernatehql.model.Purchase;
import ru.ikusov.training.skillbox.hibernatehql.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

import static ru.ikusov.training.utils.Console.p;

public class MainClass {
    private final static SessionFactory myTheOneAndTheOnlySessionFactory;

    static {
        myTheOneAndTheOnlySessionFactory = createTheOneAndTheOnlySessionFactory();
    }

    public static void main(String... jagaldgjjlag) {
        p("Hello world!");
        test1();
    }

    public static void test1() {
        List<Course> courses;
        List<Student> students;
        List<Purchase> purchases;
        List<LinkedPurchase> linkedPurchases = new ArrayList<>();

        EntityManager entityManager = myTheOneAndTheOnlySessionFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try (Session session = getSession()) {
            courses = session.createQuery("from Course").list();
            students = session.createQuery("from Student").list();
            purchases = session.createQuery("from Purchase").list();

            for (Course course : courses) {
                for (Student student : students) {
                    for (Purchase purchase : purchases) {
                        if (purchase.getId().getCourseName().equals(course.getName()) &&
                                purchase.getId().getStudentName().equals(student.getName())) {
                            LinkedPurchase lp = new LinkedPurchase();
                            LinkedPurchase.Key key = new LinkedPurchase.Key();

                            key.setCourseId(course.getId());
                            key.setStudentId(student.getId());

                            lp.setId(key);
                            lp.setCourseName(course.getName());
                            lp.setStudentName(student.getName());
                            lp.setPrice(purchase.getPrice());
                            lp.setSubscriptionDate(purchase.getSubscriptionDate());

                            transaction.begin();
                            entityManager.persist(lp);
                            transaction.commit();
                        }
                    }
                }
            }
        }
    }

    private final static Session getSession() {
        return myTheOneAndTheOnlySessionFactory.openSession();
    }

    private final static SessionFactory createTheOneAndTheOnlySessionFactory() {
        return new MetadataSources(new StandardServiceRegistryBuilder()
                                            .configure().build())
                                    .getMetadataBuilder().build()
                .getSessionFactoryBuilder().build();
    }
}
