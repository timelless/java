package com.jpa.hibernate.jpahiberate.course;

import com.jpa.hibernate.jpahiberate.course.springdatajpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJpaCommandLineRunner implements CommandLineRunner {

//    @Autowired
//    private CourseJpaRepository repository;

    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        repository.insert(new Course(1, "Course 1", "Author 1"));
//        repository.insert(new Course(2, "Course 2", "Author 2"));
//        repository.insert(new Course(3, "Course 3", "Author 3"));
//        repository.insert(new Course(4, "Course 4", "Author 4"));
//
//        repository.deleteById(3);
//        System.out.println(repository.getById(4));

        repository.save(new Course(1, "Course 1", "Author 1"));
        repository.save(new Course(2, "Course 2", "Author 2"));
        repository.save(new Course(3, "Course 3", "Author 3"));
        repository.save(new Course(4, "Course 4", "Author 4"));

        repository.deleteById(3l);
        System.out.println(repository.findById(4l));

        System.out.println(repository.findAll());
        System.out.println(repository.count());
        System.out.println(repository.findByAuthor("Author 4"));
        System.out.println(repository.findByAuthor(""));
    }
}
