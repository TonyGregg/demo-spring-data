package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import com.genil.learning.springboot.data.demospringdata.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

/**
 * Created by genil on 6/26/18 at 11 42
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager entityManager;




    @Test
    public void retrieveStudentAndPassportDetails() {
        logger.info("Learning is fun.. fetch student and his passport");

        Student student = entityManager.find(Student.class,17L);
        logger.info("Student -> {}",student);
        logger.info("Student.passport -> {}",student.getPassport());



    }

}
