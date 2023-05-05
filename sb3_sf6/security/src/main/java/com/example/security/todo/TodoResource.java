package com.example.security.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final List<Todo> TODOS = List.of(
            new Todo("vasilk", "1st todo description"),
            new Todo("vasilk", "2nd todo description")
    );

    @GetMapping(path = "/todos")
    public List<Todo> retrieveTodos() {
        return TODOS;
    }

    @GetMapping(path = "/users/{username}/todos")
    public Todo retrieveTodosForUser(@PathVariable String username) {
        return TODOS.get(0);
    }

    @PostMapping(path = "/users/{username}/todos")
    public void createTodosForUser(@PathVariable String username, @RequestBody Todo todo) {
        logger.info("Adding {} todo for {}", todo, username);
//        TODOS.add(todo);
    }
}

record Todo (String username, String description) {}