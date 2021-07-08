package ru.ikusov.training.skillbox.hibernatentity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @EmbeddedId
    private Key id;
    public void setId(Key id) {
        this.id = id;
    }
    public Key getId() {
        return id;
    }

    @Column(name = "subscription_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionDate;
    public Date getSubscriptionDate() {
        return subscriptionDate;
    }
    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    @Embeddable
    class Key implements Serializable {

        @Column(name = "student_id")
        private Integer studentId;
        public Integer getStudentId() {
            return studentId;
        }
        public void setStudentId(Integer studentId) {
            this.studentId = studentId;
        }

        @Column(name = "course_id")
        private Integer courseId;
        public Integer getCourseId() {
            return courseId;
        }
        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(studentId, key.studentId) && Objects.equals(courseId, key.courseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }
}
