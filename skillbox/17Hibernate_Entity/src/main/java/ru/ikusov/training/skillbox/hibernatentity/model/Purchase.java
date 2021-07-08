package ru.ikusov.training.skillbox.hibernatentity.model;

import javax.persistence.*;
import java.sql.Timestamp;

//@Entity
//@Table (name = "purchaselist")
public class Purchase {
    private String studentName;
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    private String courseName;
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private Integer price;
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getPrice() {
        return price;
    }

    private Timestamp subscriptionDate;
    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }
    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
