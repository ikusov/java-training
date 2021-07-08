package ru.ikusov.training.skillbox.hibernatentity.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Entity
@Table (name = "courses")
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    public Integer getId() {return id;}

    @Column(name = "name")
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Column(name = "duration")
    private Integer duration;
    public Integer getDuration() {return duration;}
    public void setDuration(Integer duration) {this.duration = duration;}

    @Column(name = "type", columnDefinition = "enum")
    private String type;
    public String getType() {return type;}
    public void setType(String type) {
        this.type = type;
    }

    @Column (name="description")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column (name = "students_count")
    private Integer studentsCount;
    public Integer getStudentsCount() {
        return studentsCount;
    }
    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    @Column (name = "price")
    private Integer price;
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column (name = "price_per_hour")
    private Float pricePerHour;
    public Float getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(Float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "course")
    private Set<Subscription> subscriptions = new HashSet<>();
    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }
    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @ManyToMany
    @JoinTable(name = "subscriptions",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();
    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String toString() {
        return String.format("Course name: %s,\n\tstudents count: %d", name, studentsCount);
    }

    public String toExtendedString() {
        try {
            return this + "\n\tstudents:"
                    + students.stream()
                    .map(student -> String.format("\n\t\t%s", student))
                    .reduce((s1, s2) -> s1 + s2).get();
        } catch (NoSuchElementException e) {
            return this + "\n\tcourse has no students";
        }
    }
}
