package com.genil.learning.springboot.data.demospringdata.entity;

import javax.persistence.*;

/**
 * Created by genil on 6/28/18 at 04 33
 **/
@Entity
public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private Long id;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport=" + passport +
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

    @OneToOne
    private Passport passport;
}
