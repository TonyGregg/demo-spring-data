package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import com.genil.learning.springboot.data.demospringdata.entity.Passport;
import com.genil.learning.springboot.data.demospringdata.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

/**
 * Created by genil on 6/26/18 at 09 49
 **/


@Repository
@Transactional
public class StudentRepository {
    @Autowired
    EntityManager entityManager;

    /**
     * findById(Long id);
     * public Student save(Student student) -> insert or update
     *
     * public void deleteBydId(Long id)
     */

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    /**
     * MANDATORY
     * Support a current transaction, throw an exception if none exists.
     * NESTED
     * Execute within a nested transaction if a current transaction exists, behave like PROPAGATION_REQUIRED else.
     * NEVER
     * Execute non-transactionally, throw an exception if a transaction exists.
     * NOT_SUPPORTED
     * Execute non-transactionally, suspend the current transaction if one exists.
     * REQUIRED
     * Support a current transaction, create a new one if none exists.
     * REQUIRES_NEW
     * Create a new transaction, and suspend the current transaction if one exists.
     * SUPPORTS
     * Support a current transaction, execute non-transactionally if none exists.
     * @param student
     * @return
     */

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Student save(Student student) {
        if(student==null || student.getId() ==null) {
             entityManager.persist(student);

        }else {
            entityManager.merge(student);
        }

//        entityManager.detach(student); // will make the transaction not get updated below
        //other option is
//        entityManager.clear();

        student.setName(student.getName()+"updated .."+LocalDateTime.now());

        return student;

    }
    public void saveStudentWithPassport() {
        Passport passport = new Passport("Passport B");
        entityManager.persist(passport);

        Student student = new Student("Student B");

        student.setPassport(passport);

        entityManager.persist(student);

    }

    public void insertStudentAndCourse() {
        Student student = new Student("Jack Moan");
        Course course = new Course("Microservices in 100 steps");
        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);

    }

    public Passport findPassportById(Long id) {
        return entityManager.find(Passport.class,id);
    }
}
