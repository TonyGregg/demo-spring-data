package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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




}

