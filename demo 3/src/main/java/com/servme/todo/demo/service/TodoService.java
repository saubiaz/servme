package com.servme.todo.demo.service;

import com.servme.todo.demo.exception.ResourceNotFoundException;
import com.servme.todo.demo.model.Category;
import com.servme.todo.demo.model.Todo;
import com.servme.todo.demo.model.TodoStatus;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<Todo> todoListByUserId(Long userId);

    Optional<Todo> findById(Long todoId);

    Todo save(Long userId,Todo todo) throws ResourceNotFoundException;

    void delete(Long userId,Long todoId) throws ResourceNotFoundException;

    Todo updateTodo(Long userId,Long todoId,Todo todo) throws ResourceNotFoundException;

    List<Todo> getTodoByIdByDate(Long userId, String month) throws ResourceNotFoundException, ParseException;

    List<Todo> getTodoByIdByCategory(Long userId, Category category) throws ResourceNotFoundException;

    List<Todo> getTodoByIdByStatus(Long userId, TodoStatus status) throws ResourceNotFoundException;
}

