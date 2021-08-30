package com.servme.todo.demo.service;

import com.servme.todo.demo.exception.AuthenticationException;
import com.servme.todo.demo.exception.ResourceNotFoundException;
import com.servme.todo.demo.model.Category;
import com.servme.todo.demo.model.Todo;
import com.servme.todo.demo.model.TodoStatus;
import com.servme.todo.demo.model.User;
import com.servme.todo.demo.repository.TodoRepository;
import com.servme.todo.demo.repository.UserRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Todo> todoListByUserId(Long userId) {
        return todoRepository.findByUserId(userId);
    }

    @Override
    public Optional<Todo> findById(Long todoId) {
        return todoRepository.findById(todoId);
    }

    @Override
    public Todo save(Long userId,Todo todo) throws ResourceNotFoundException {

        final Todo userSave = userRepository
            .findById(userId)
            .filter(User::isLoggedIn)
            .map(user -> {
                todo.setUser(user);
                return todoRepository.save(todo);
            })
            .orElseThrow(() -> new ResourceNotFoundException("User not authenticated"));

        if(userSave==null) {
            throw new AuthenticationException("User not authenticated");
        }else return userSave;

    }

    @Override
    public void delete(Long userId,Long todoId) throws ResourceNotFoundException {

        final Optional<Todo> updatedTodo = Optional
            .ofNullable(userRepository.findById(userId))
            .orElseThrow(() -> new ResourceNotFoundException("User Id " + userId + " not found"))
            .filter(User::isLoggedIn)
            .map(user -> todoRepository
                .findById(todoId)
                .map( todo -> {todoRepository.delete(todo);
                    return todo;
                })
                .orElseThrow(() -> new AuthenticationException("Comment id not found")));

        if(!updatedTodo.isPresent())
         throw new AuthenticationException("User not authenticated");

    }

    @Override
    public Todo updateTodo(Long userId,Long todoId, Todo todo) throws ResourceNotFoundException {

        final Optional<Todo> updatedTodo = Optional
            .ofNullable(userRepository.findById(userId))
            .orElseThrow(() -> new ResourceNotFoundException("User Id " + userId + " not found"))
            .filter(User::isLoggedIn)
            .map(user -> todoRepository
                .findById(todoId)
                .map(t -> {
                    t.setTodoStatus(TodoStatus.valueOf(todo.getTodoStatus().toString()));
                    t.setDescription(todo.getDescription());
                    t.setTargetDate(todo.getTargetDate());
                    return todoRepository.save(t);
                })
                .orElseThrow(() -> new AuthenticationException("Comment id not found")));

       if(updatedTodo.isPresent()){
           return updatedTodo.get();
       }else throw new AuthenticationException("User not authenticated");
    }

    @Override
    public List<Todo> getTodoByIdByDate(Long userId, String date) throws ResourceNotFoundException, ParseException {
        String pattern = "yyyy-MM-dd";
        DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date formatDate = simpleDateFormat.parse(date);;
        final Optional<List<Todo>> updatedTodo = Optional
            .ofNullable(userRepository.findById(userId))
            .orElseThrow(() -> new ResourceNotFoundException("User Id " + userId + " not found"))
            .filter(User::isLoggedIn)
             .map(user -> todoRepository.findByUserId(userId).stream().filter( todo -> todo.getTargetDate()
                 .before(formatDate)).collect(Collectors.toList()));

        if(updatedTodo.isPresent()){
            return updatedTodo.get();
        }else throw new AuthenticationException("User not authenticated");
    }


    @Override
    public List<Todo> getTodoByIdByCategory(Long userId, Category category) throws ResourceNotFoundException {

        final Optional<List<Todo>> updatedTodo = Optional
            .ofNullable(userRepository.findById(userId))
            .orElseThrow(() -> new ResourceNotFoundException("User Id " + userId + " not found"))
            .filter(User::isLoggedIn)
            .map(user -> todoRepository.findByUserId(userId).stream().filter( todo -> Objects.equals(todo
                .getCategory(),category)).collect(Collectors.toList()));

        if(updatedTodo.isPresent()){
            return updatedTodo.get();
        }else throw new AuthenticationException("User not authenticated");
    }

    @Override
    public List<Todo> getTodoByIdByStatus(Long userId, TodoStatus status) throws ResourceNotFoundException {

        final Optional<List<Todo>> updatedTodo = Optional
            .ofNullable(userRepository.findById(userId))
            .orElseThrow(() -> new ResourceNotFoundException("User Id " + userId + " not found"))
            .filter(User::isLoggedIn)
            .map(user -> todoRepository.findByUserId(userId).stream().filter( todo -> Objects.equals(todo
                .getTodoStatus().name(),status)).collect(Collectors.toList()));

        if(updatedTodo.isPresent()){
            return updatedTodo.get();
        }else throw new AuthenticationException("User not authenticated");
    }
}
