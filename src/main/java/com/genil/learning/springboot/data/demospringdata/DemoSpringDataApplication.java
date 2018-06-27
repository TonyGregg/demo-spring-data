package com.genil.learning.springboot.data.demospringdata;

import com.genil.learning.springboot.data.demospringdata.entity.Course;
import com.genil.learning.springboot.data.demospringdata.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringDataApplication implements CommandLineRunner {
	@Autowired
	CourseRepository courseRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());



	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Course course =  courseRepository.findById(100L);

		logger.info(" Course 100 {} ",course);

	}
}
