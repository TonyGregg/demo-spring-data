package com.genil.learning.springboot.data.demospringdata;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import com.genil.learning.springboot.data.demospringdata.entity.Review;
import com.genil.learning.springboot.data.demospringdata.repository.CourseRepository;
import com.genil.learning.springboot.data.demospringdata.repository.ReviewsRepository;
import com.genil.learning.springboot.data.demospringdata.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.List;

@EnableCaching
@SpringBootApplication
public class DemoSpringDataApplication implements CommandLineRunner {
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	ReviewsRepository reviewsRepository;



	private Logger logger = LoggerFactory.getLogger(this.getClass());



	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		studentRepository.saveStudentWithPassport();

//		logger.info(" Course 100 {} ",course);

/*		logger.info("Finding passport by id");
		Passport passport = studentRepository.findPassportById(5L);

		logger.info("Passport -> {}",passport);

		Student student = passport.getStudent();

		logger.info("Passport.Student -> {}",student);*/

//		logger.info("Find all the reviews based on review id");
//
//		Course course = courseRepository.findById(101L);
//
//		logger.info("Course -> {} "+course);

//		Review review1 = new Review(5,"101 Great hands-on stuff");
//		Review review2 = new Review(5,"101 Hatsoff buddy");
//
//		List<Review> reviews = new ArrayList<>();
//		reviews.add(review1);
//		reviews.add(review2);
//
//
//		courseRepository.addReviewsForCourse(101L, reviews);



/*
		logger.info("Going to search review by id");

		Review review = reviewsRepository.findById(812L);
		logger.info("Review {}",review);
*/
//		logger.info("Adding student and course ");
//		studentRepository.insertStudentAndCourse();
//		logger.info("Added. . .");


	}
}
