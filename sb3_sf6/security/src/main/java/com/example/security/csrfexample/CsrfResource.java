package com.example.security.csrfexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CsrfResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static List<Todo> TODOS = List.of(
            new Todo("user1", "description 1"),
            new Todo("user2", "description 2")
    );

    @GetMapping(path = "todos")
    public List<Todo> retrieveAllTodos() {
        return this.TODOS;
    }

    @GetMapping(path = "/users/{username}/todos")
    public Todo getTodosForUser(@PathVariable String username) {
        return this.TODOS.get(0);
    }

    @PostMapping(path = "/users/{username}/todos")
    public void createTodoForUser(@PathVariable String username, @RequestBody Todo todo) {
        logger.info("Creating... {}, {}", todo, username);
    }
}

record Todo(String username, String description) {}
