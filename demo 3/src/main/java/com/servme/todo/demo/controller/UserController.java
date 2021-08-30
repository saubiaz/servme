package com.servme.todo.demo.controller;

import com.servme.todo.demo.exception.ResourceNotFoundException;
import com.servme.todo.demo.model.User;
import com.servme.todo.demo.service.UserService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user) throws ResourceNotFoundException {
        User createdUser = userService.register(user);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/id").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) throws ResourceNotFoundException {
        User loggedInUser = userService.login(user.getEmail(), user.getPassword());
        if(loggedInUser.isLoggedIn()) {
            return ResponseEntity.ok("User LoggedIn");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(@RequestBody User user) throws ResourceNotFoundException {
        User loggedOutUser = userService.logout(user.getEmail());
        if(!loggedOutUser.isLoggedIn()) {
            return ResponseEntity.ok("User Logged Out");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestBody User user) throws ResourceNotFoundException {
       userService.delete(user.getEmail());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestBody User user) throws ResourceNotFoundException {
       return ResponseEntity.ok(userService.getUser(user.getEmail()));
    }
}
