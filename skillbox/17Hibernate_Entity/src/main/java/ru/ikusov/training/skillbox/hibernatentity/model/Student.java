package ru.ikusov.training.skillbox.hibernatentity.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "students")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    public Integer getId() {
        return id;
    }

    @Column (name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column (name = "age")
    private Integer age;
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @Column (name = "registration_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date registrationDate;
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    @OneToMany(mappedBy = "student")
    private Set<Subscription> subscriptions = new HashSet<>();
    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }
    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();
    public Set<Course> getCourses() {
        return courses;
    }
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return String.format("Student name: %s, student age: %d", name, age);
    }
}
