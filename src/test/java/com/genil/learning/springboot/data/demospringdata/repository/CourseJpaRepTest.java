package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import net.bytebuddy.TypeCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by anton on 6/30/2018 6:41 PM
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseJpaRepTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseJPARepository courseJPARepository;

    @Test
    public void findByIdIsCoursePresent() {
        Optional<Course> courseOptional =  courseJPARepository.findById(100L);
        assertTrue(courseOptional.isPresent());
        logger.info("{} ",courseOptional.isPresent());
    }

    @Test
    public void findByIdIsCourseNotPresent() {
        Optional<Course> courseOptional =  courseJPARepository.findById(102320L);
        assertFalse(courseOptional.isPresent());
        logger.info("{} ",courseOptional.isPresent());
    }
    @Test
    public void playWithJpaRep() {
        Course course = new Course("Microservices in 100 steps using JPA");
       courseJPARepository.save(course);

       course.setName("UPdated micro services JPA 22");
       courseJPARepository.save(course);
       //other methods
        logger.info("{}"+courseJPARepository.findAll());
        logger.info("{}"+courseJPARepository.count());
    }

    @Test
    public void playSorting() {
        Sort sort = new Sort(Sort.Direction.DESC,"name");
        logger.info("Course sorted {} "+courseJPARepository.findAll(sort));
    }

    @Test
    public void testPagination() {
        // Divide it into pages - 3

        PageRequest pageRequest = PageRequest.of(0,5);
        Page<Course> firstPage = courseJPARepository.findAll(pageRequest);

        logger.info("First page  {} "+firstPage.getContent());

        logger.info("Second  page {} "+courseJPARepository.findAll(firstPage.nextPageable()).getContent());
        logger.info("3rd   page {} "+courseJPARepository.findAll(firstPage.nextPageable()).getContent());


    }

    @Test
    @Transactional
    public void findByName() {
        List<Course> courseList = courseJPARepository.findByName("Business communication 101");
        logger.info("Find by name {}"+courseList);
        List<Course> courseList1 = courseJPARepository.findByName("Business communication 101");
        logger.info("2nd attempt. .. Find by name {}"+courseList);
    }

    @Test
    public void useNamedQueryJpa() {
        List<Course> courseList = courseJPARepository.getAllCoursesMatchingQuery();
        logger.info("Matching all query --> {}",courseList);
    }
}
