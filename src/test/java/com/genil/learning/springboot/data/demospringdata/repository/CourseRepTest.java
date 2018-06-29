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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by genil on 6/26/18 at 11 42
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;




   /* @DirtiesContext
    @Test
    public void deleteById() {


        logger.info("Deleting course 102...");
        courseRepository.deleteById(102L);

        assertNull(courseRepository.findById(102L));
    }

*/
    @Test
    public void findById() {
        logger.info("Context loaded. ..");
        Course course = courseRepository.findById(102L);
        logger.info("Course fetched {}",course);

        assertEquals("Spring Cache",course.getName());


    }

    @Test
    public void save() {
        Course course = new Course("Course by Gen");
        course.setAuthorName("Tony Greg !!");

        courseRepository.save(course);
    }

    @Test
    public void fetchAndUpdate() {
        logger.info("Learning is fun.. fetch and update");
        Course course = courseRepository.findById(207L);
        logger.info("I found course {} ",course);

        course.setName("Time interval anlyzis lighnting flashes");
        courseRepository.save(course);

    }

    @Test
    public void updateTest(){
        courseRepository.updateRecords();
    }

}
