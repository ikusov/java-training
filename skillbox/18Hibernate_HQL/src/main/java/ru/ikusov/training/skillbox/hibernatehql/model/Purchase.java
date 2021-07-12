package ru.ikusov.training.skillbox.hibernatehql.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "purchaselist")
public class Purchase {
    @EmbeddedId
    private Key id;
    public void setId(Key id) {
        this.id = id;
    }
    public Key getId() {
        return id;
    }

    @Column(name = "price")
    private Integer price;
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getPrice() {
        return price;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(studentName, key.studentName) && Objects.equals(courseName, key.courseName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentName, courseName);
        }
    }
}
