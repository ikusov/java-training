package ru.ikusov.training.skillbox.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue()
    @Column(name = "id")
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    @Column(name = "duration")
    private int duration;
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Column(name = "type")
    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "description")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "teacher_id")
    private int teacherId;
    public int getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Column(name = "students_count")
    private Integer studentsCount;
    public Integer getStudentsCount() {
        return studentsCount;
    }
    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    @Column(name = "price")
    private int price;
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "price_per_hour")
    private float pricePerHour;
    public float getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String toString() {
        return String.format("Course name: %s, course type: %s, students count: %d", name, type, studentsCount);
    }
}
