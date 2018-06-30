package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import com.genil.learning.springboot.data.demospringdata.entity.Student;
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

    @Test
    public void jpql_course_with_no_students() {
        TypedQuery<Course> courseTypedQuery = entityManager.createQuery("select c from Course c " +
                "where c.students is empty",Course.class);
        List<Course> courses = courseTypedQuery.getResultList();

        logger.info("# of courses without students {}, and their list --> {}",courses.size(),courses);
    }
    @Test
    public void courseWithAtleast2Sudents() {
        TypedQuery<Course> courseTypedQuery = entityManager.createQuery("select c from Course c " +
                "where size(c.students) >=1",Course.class);
        List<Course> courses = courseTypedQuery.getResultList();

        logger.info("# of courses more than 1 students {},\n\n and their list --> {}",courses.size(),courses);

    }

    @Test
    public void courseOrderBy() {
        TypedQuery<Course> courseTypedQuery = entityManager.createQuery("select c from Course c " +
                "order by size(c.students) desc",Course.class);
        List<Course> courses = courseTypedQuery.getResultList();

        logger.info("# of order by {},\n\n and their list --> {}",courses.size(),courses);

    }

    @Test
    public void jpqlWithPassortSomePattern(){
        TypedQuery<Student> courseTypedQuery = entityManager.createQuery("select s from Student s " +
                "where s.passport.name like '%1%'",Student.class);
        List<Student> students = courseTypedQuery.getResultList();

        logger.info("# of order by {},\n\n and their list --> {}",students.size(),students);
    }

    //Simple join Select c, s from Course c JOIN c.students s
    //Left join Select c  from Course c LEFT JOIN c.students s
    //Cross join select c,s from Course c, Student s
    // 3, 4 then -==> 3*4 = 12 Rows
    @Test
    public void testJoins() {
        Query query = entityManager.createQuery("Select c,s from Course c JOIN  c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Size "+resultList.size());

        for (Object[] result: resultList) {
            // result[0] :: course
            // result [1] : student
            logger.info("Course {}\n Student {} ",result[0],result[1]);


        }

    }

    @Test
    public void testLeftJoins() {
        Query query = entityManager.createQuery("Select c,s from Course c LEFT JOIN  c.students s"); // Show students with null (no courses assigned) also
        List<Object[]> resultList = query.getResultList();
        logger.info("Size "+resultList.size());

        for (Object[] result: resultList) {
            // result[0] :: course
            // result [1] : student
            logger.info("Course {}\n Student {} ",result[0],result[1]);


        }

    }

    @Test
    public void testCrossJoins() {
        Query query = entityManager.createQuery("Select c,s from Course c,Student s"); //Cross join
        List<Object[]> resultList = query.getResultList();
        logger.info("Size "+resultList.size());

        for (Object[] result: resultList) {
            // result[0] :: course
            // result [1] : student
            logger.info("Course {}\n Student {} ",result[0],result[1]);


        }

    }




}

