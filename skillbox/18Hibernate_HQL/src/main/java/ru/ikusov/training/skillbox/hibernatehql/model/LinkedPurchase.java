package ru.ikusov.training.skillbox.hibernatehql.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "linked_purchaselist")
public class LinkedPurchase {
    @EmbeddedId
    private Key id;
    public Key getId() {
        return id;
    }
    public void setId(Key id) {
        this.id = id;
    }

    @Column(name = "student_name")
    private String studentName;
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Column(name = "course_name")
    private String courseName;
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Column(name = "price")
    private Integer price;
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "subscription_date")
    private Date subscriptionDate;
    public Date getSubscriptionDate() {
        return subscriptionDate;
    }
    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Embeddable
    public static class Key implements Serializable {
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
