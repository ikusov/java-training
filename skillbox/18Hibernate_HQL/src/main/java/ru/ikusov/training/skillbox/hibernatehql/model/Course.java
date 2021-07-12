package ru.ikusov.training.skillbox.hibernatehql.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    public Integer getId() {
        return id;
    }

    @Column(name = "name")
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
