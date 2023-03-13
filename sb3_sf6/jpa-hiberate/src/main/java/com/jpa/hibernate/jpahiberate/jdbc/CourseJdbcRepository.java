package com.jpa.hibernate.jpahiberate.jdbc;

import com.jpa.hibernate.jpahiberate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {
    @Autowired
    private JdbcTemplate springJdbc;

    private static String INSERT_QUERY =
                    """
                        INSERT INTO course (id, name, author) VALUES (?, ?, ?);
                    """;
    private static String DELETE_QUERY =
            """
                DELETE FROM course WHERE id = ?;
            """;

    private static String SELECT_QUERY =
            """
                SELECT * FROM course WHERE id = ?;
            """;

    public void insert(Course course) {
        springJdbc.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(long id) {
        springJdbc.update(DELETE_QUERY, id);
    }

    public Course getById(long id) {
        // RS -> Bean => Row mappers
        return springJdbc.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<> (Course.class), id);

    }
}
