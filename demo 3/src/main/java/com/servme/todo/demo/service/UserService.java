package com.servme.todo.demo.service;

import com.servme.todo.demo.exception.ResourceNotFoundException;
import com.servme.todo.demo.model.User;

public interface UserService {

    public User register(User user) throws ResourceNotFoundException;
    public User login(String email,String password) throws ResourceNotFoundException;
    public User logout(String firstName) throws ResourceNotFoundException;

    public void delete(String email) throws ResourceNotFoundException;
    User getUser(String email) throws ResourceNotFoundException;
}
