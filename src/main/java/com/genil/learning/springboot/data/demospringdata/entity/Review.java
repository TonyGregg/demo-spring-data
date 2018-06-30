package com.genil.learning.springboot.data.demospringdata.entity;

import javax.persistence.*;

/**
 * Created by genil on 6/28/18 at 04 37
 **/
@Entity
@Table(name = "Reviews")
public class Review {


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }

    protected Review() {

    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String comment;

    @ManyToOne
    private Course course;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int rating;


}
