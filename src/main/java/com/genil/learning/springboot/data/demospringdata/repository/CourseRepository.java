package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

/**
 * Created by genil on 6/26/18 at 09 49
 **/


@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager entityManager;

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
}
