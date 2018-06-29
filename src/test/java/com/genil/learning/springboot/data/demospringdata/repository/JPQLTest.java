package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.junit.Assert.assertEquals;

/**
 * Created by genil on 6/26/18 at 15 18

*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;


    @Test
    public void playWithQueries() {
        logger.info("Playing with queries . ..");
        TypedQuery<Course> courseTypedQuery = entityManager.createQuery("Select c From Course c",Course.class);
        List<Course> courseList  = courseTypedQuery.getResultList();

        logger.info("List ... {} "+courseList);


    }

    @Test
    public void playWithNamedQueries() {
        logger.info("Playing with named queries");
        TypedQuery<Course> courseTypedQuery = entityManager.createNamedQuery("query_get_all_courses",Course.class);
        List<Course> courseList = courseTypedQuery.getResultList();
        logger.info("Named Query :: List ... {} "+courseList);

    }

    @Test
    public void playWithNativeBasics() {
        Query query = entityManager.createNativeQuery("Select * from Course",Course.class);
        List<Course> courseList = query.getResultList();

        logger.info("Native Query :: List ... {} "+courseList);
    }

    @Test
    @Transactional
    @Modifying(clearAutomatically = true)
    public void doBulkUpdateTest() {
        Query query = entityManager.createNativeQuery("update  Course set created_date = SYSDATE() -1");
        int res = query.executeUpdate();
        entityManager.flush();
        entityManager.clear();
        logger.info("Native Query Update result : {} "+res);
    }




}

