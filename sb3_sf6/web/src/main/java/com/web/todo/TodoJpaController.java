package com.web.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoJpaController {
    private TodoService todoService;

    private TodoRepository todoRepository;

    public TodoJpaController(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @RequestMapping("list-todos")
    public String listAll(ModelMap model) {
        List<Todo> todos = todoRepository.findByUsername(getLoggedUserUsername());
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showTodoDetails(ModelMap model) {
        Todo todo = new Todo(0, getLoggedUserUsername(), "Default", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);

        return "todo";
    }

    // Command bean
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNew(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "todo";
        }

//        todoService.addTodo(getLoggedUserUsername(), todo.getDescription(), todo.getTargetDate(), todo.isDone());

        todo.setUsername(getLoggedUserUsername());
        todoRepository.save(todo);

        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(int id) {
//        todoService.deleteById(id);
        todoRepository.deleteById(id);

        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoDetails(int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.put("todo", todo);

        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "todo";
        }

        todo.setUsername(getLoggedUserUsername());
//        todoService.updateTodo(todo);
        todoRepository.save(todo);


        return "redirect:list-todos";
    }

    private String getLoggedUserUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}
