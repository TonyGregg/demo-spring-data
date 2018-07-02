package com.genil.learning.springboot.data.demospringdata.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by genil on 6/28/18 at 04 33
 **/
@Entity
public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private Long id;

    @OneToOne (fetch = FetchType.LAZY)
    private Passport passport;

    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private List<Course> courses = new ArrayList<>();

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name= '" + name + '\'' +
                '}';
    }

    public Student(String name) {
        this.name = name;
    }
    protected Student() {

    }

    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }


}
