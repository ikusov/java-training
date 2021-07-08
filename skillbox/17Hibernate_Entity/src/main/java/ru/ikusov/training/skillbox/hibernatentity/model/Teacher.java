package ru.ikusov.training.skillbox.hibernatentity.model;

import javax.persistence.*;
import java.util.NoSuchElementException;
import java.util.Set;

@Entity
@Table (name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "salary")
    private Integer salary;
    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Column(name = "age")
    private Integer age;
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;
    public Set<Course> getCourses() {
        return courses;
    }
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return String.format("Teacher name: %s,\n\tteacher age: %d", name, age);
    }

    public String toExtendedString() {
        try {
            return this + "\n\tteacher courses:"
                    + courses.stream()
                    .map(course -> String.format("\n\t\t%s", course.toExtendedString()))
                    .reduce((s1, s2) -> s1 + s2)
                    .get();
        } catch (NoSuchElementException e) {
            return this + "\n\tteacher has no courses";
        }
    }
}
