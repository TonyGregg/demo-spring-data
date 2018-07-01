package com.genil.learning.springboot.data.demospringdata.repository;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by anton on 6/30/2018 6:40 PM
 **/

@RepositoryRestResource(path = "courses") // not recommended in production
public interface CourseJPARepository extends JpaRepository<Course,Long> {

    List<Course> findByName(String name);
    //Using query in JPA
    @Query(name = "query_get_all_courses")
    List<Course> getAllCoursesMatchingQuery();


    // Use plain query
    @Query("Select c from Course c")
    List<Course> getAllCourses();

    @Query(value = "Select * from Course",nativeQuery = true)
    List<Course> getAllCoursesUsingNativeQuery();


}
