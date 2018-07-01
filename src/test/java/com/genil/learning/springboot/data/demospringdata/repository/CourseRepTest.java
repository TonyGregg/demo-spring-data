package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import com.genil.learning.springboot.data.demospringdata.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import java.util.List;

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
    @Autowired
    EntityManager entityManager;




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
    @Transactional
    public void retrieveAllReviewsForACourse() {
        Course course = courseRepository.findById(101L);
        logger.info("First attmepmpt ..{}", course.getReviews());

        Course course2 = courseRepository.findById(101L);
        logger.info("2nd attmepmpt ..{}", course2.getReviews());
    }

    @Test
    @Transactional
    public void testNplusOne() {
        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses",Course.class).getResultList();
        logger.info("# of courses "+courses.size());
        for (Course course: courses) {
            logger.info("Course -> {} Students -> {} ",course,course.getStudents());
        }
    }


    @Test
    @Transactional
    public void solveNPlusOneProblemGraph() {
        EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
        Subgraph<Student> courseSubgraph = entityGraph.addSubgraph("students");

        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses",Course.class)
                .setHint("javax.persistence.loadgraph",entityGraph)
                .getResultList();
        logger.info("# of courses "+courses.size());
        for (Course course: courses) {
            logger.info("Course -> {} Students -> {} ",course,course.getStudents());
        }
    }


    @Test
    @Transactional
    public void solveNPlusOneProblemJoinFetch() {

        List<Course> courses = entityManager.createNamedQuery("query_get_all_courses_join_fetch",Course.class)
                .getResultList();
        logger.info("# of courses "+courses.size());
        for (Course course: courses) {
            logger.info("Course -> {} Students -> {} ",course,course.getStudents());
        }
    }

    @Test
    public void updateTest(){
        courseRepository.updateRecords();
    }

}
