package com.servme.todo.demo.controller;

import antlr.StringUtils;
import com.servme.todo.demo.exception.ResourceNotFoundException;
import com.servme.todo.demo.model.Category;
import com.servme.todo.demo.model.Todo;
import com.servme.todo.demo.model.TodoStatus;
import com.servme.todo.demo.service.TodoService;
import com.servme.todo.demo.service.UserService;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;


    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/users/{userId}/todos")
    public List<Todo> getTodoListByUserId(@PathVariable Long userId) {
        return todoService.todoListByUserId(userId);
    }

    @GetMapping("users/{userId}/todos/{id}")
    public ResponseEntity<Todo> getTodoById( @PathVariable String userId, @PathVariable(value = "id") Long todoId)
        throws ResourceNotFoundException {

        Todo todo = todoService.findById(todoId)
            .orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));
        return ResponseEntity.ok().body(todo);
    }

    @GetMapping("users/{userId}/todos/{date}")
    public List<Todo> getTodoByIdByMonth( @PathVariable Long userId,@PathVariable(value = "date") String date)
        throws ResourceNotFoundException, ParseException {
        return todoService.getTodoByIdByDate(userId,date);
    }

    @GetMapping("users/{userId}/todos/getByCriteria")
    public List<Todo> getTodoByIdByCategory( @PathVariable Long userId,@Valid @RequestBody Todo todo)
        throws ResourceNotFoundException{
        if(null!=todo.getCategory()) {
            return todoService.getTodoByIdByCategory(userId, todo.getCategory());
        }else
            return todoService.getTodoByIdByStatus(userId,todo.getTodoStatus());
    }

    @PostMapping("/users/{userId}/todos")
    public Todo createTodo(@PathVariable Long userId, @Valid @RequestBody Todo todo) throws ResourceNotFoundException {
        todo.setCreatedDate(new Date());
        return todoService.save(userId, todo);
    }

    @PutMapping("/users/{userId}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable(value = "userId") Long userId, @PathVariable(value = "id") Long todoId,
        @Valid @RequestBody Todo todoDetails) throws ResourceNotFoundException {
        Todo todo = todoService.updateTodo(userId,todoId,todoDetails);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/users/{userId}/todos/{id}")
    public Map<String, Boolean> deleteTodo(@PathVariable(value = "userId") Long userId,@PathVariable(value = "id") Long todoId)
        throws ResourceNotFoundException {
        todoService.delete(userId,todoId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
