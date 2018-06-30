package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import com.genil.learning.springboot.data.demospringdata.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;

/**
 * Created by genil on 6/26/18 at 09 49
 **/

// Making @Repository is important.. if you miss it, then the application will not be able to autowire reviewRepository

@Repository
public class ReviewsRepository {
    @Autowired
    EntityManager entityManager;

    /**
     * findById(Long id);
     * public Course save(Course course) -> insert or update
     *
     * public void deleteBydId(Long id)
     */

    public Review findById(Long id) {
        return entityManager.find(Review.class, id);
    }




}
