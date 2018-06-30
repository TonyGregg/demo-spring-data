package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import com.genil.learning.springboot.data.demospringdata.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by genil on 6/26/18 at 09 49
 **/


@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * findById(Long id);
     * public Course save(Course course) -> insert or update
     *
     * public void deleteBydId(Long id)
     */

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public Course save(Course course) {
        if(course==null || course.getId() ==null) {
             entityManager.persist(course);

        }else {
            entityManager.merge(course);
        }

//        entityManager.detach(course); // will make the transaction not get updated below
        //other option is
//        entityManager.clear();

        course.setName(course.getName()+"updated .."+LocalDateTime.now());

        return course;

    }

    public int updateRecords() {
        Query query = entityManager.createNativeQuery("update  Course set created_date = SYSDATE() -1");
        int res = query.executeUpdate();
        entityManager.flush();
        return res;
    }

//    public List<Review> getAllReviews(Course course) {
//        List<Review> reviews = entityManager.find(Course.class,course.getId()).getReviews();
//        return reviews;
//    }

    public void addReviewsForCourse(Long courseId) {
        //1. Get the course
        Course course = findById(courseId);
        logger.info(" course.getReviews() {} "+course.getReviews());

        // Add 2 reviews
        Review review1 = new Review(5,"Great hands-on stuff");
        Review review2 = new Review(5,"Hatsoff buddy");

        // Set the relation
        review1.setCourse(course);

        review2.setCourse(course);

        // Save it to the DB
        course.addReview(review1);
        course.addReview(review2);

        entityManager.persist(review1);
        entityManager.persist(review2);


    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        //1. Get the course
        Course course = findById(courseId);
        logger.info(" course.getReviews() {} "+course.getReviews());

        // Add  reviews
        for(Review review:reviews) {
            // set the course
            review.setCourse(course);
            course.addReview(review);
            entityManager.persist(review);
        }





    }

}
