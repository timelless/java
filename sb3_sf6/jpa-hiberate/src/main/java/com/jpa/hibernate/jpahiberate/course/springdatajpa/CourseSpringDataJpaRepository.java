package com.jpa.hibernate.jpahiberate.course.springdatajpa;

import com.jpa.hibernate.jpahiberate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {
    List<Course> findByAuthor(String author);
}
