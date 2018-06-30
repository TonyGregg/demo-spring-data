package com.genil.learning.springboot.data.demospringdata.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by genil on 6/26/18 at 09 45
 **/

/**
 * @Entity makes it a real entity
 */
@Entity(name = "Course") // Entity name used in queries and everywhere else
@Table(name = "Course") // Name of the table in DB
//@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
@NamedQuery(name = "query_get_all_courses", query = "Select c from Course c")
/**
 * For more than one @NamedQuery, use
 * @NamedQueries(value = {
     * @NamedQuery(name="query_1","select * from sleep"),
     * @NamedQuery("name="query 2", "Select * from yawn")
 *
 * } )
 */
//@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)

public class Course {
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }

    //protected makes sure other class won't be able to use it
    protected Course() {

    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    //Primary key is important
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "conducted_by", length = 25)
    private String authorName;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "last_updated_date")
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "course")
    List<Review> reviews = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @ManyToMany(mappedBy = "courses")
    List<Student> students = new ArrayList<>();



}
