package com.springboot.test.springboottest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CurseController {

    //localhost:8080/courses
    @RequestMapping("/courses")
    public List<Course> retrievelAllCourses() {
        return Arrays.asList(
                new Course(1, "Course 1", "author1"),
                new Course(2, "Course 2", "author2"),
                new Course(3, "Course 3", "author3")
        );
    }
}
