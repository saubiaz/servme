package com.servme.todo.demo.service;

import com.servme.todo.demo.exception.ResourceNotFoundException;
import com.servme.todo.demo.model.User;
import com.servme.todo.demo.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired UserRepository userRepository;

    @Override
    public User register(final User user) throws ResourceNotFoundException {
        return Optional.of(isPresent(user.getEmail()))
            .map( u->userRepository.save(user))
            .orElseThrow(()-> new ResourceNotFoundException("Already registered"));
    }

    private Optional<User> isPresent(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User login(String email, String password) throws ResourceNotFoundException {

        final User userFound = Optional
            .of(isPresent(email))
            .get()
            .map(user -> {
                if (user
                    .getPassword()
                    .equals(password)) {
                    user.setLoggedIn(true);
                    return userRepository.save(user);
                }
                return null;
            })
            .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

        if (null == userFound) {
            throw new ResourceNotFoundException("Incorrect Password");
        } else return userFound;
    }

    @Override public User logout(String emailId) throws ResourceNotFoundException {
        return Optional
            .of(isPresent(emailId))
            .get()
            .map(user -> {
                user.setLoggedIn(false);
                return userRepository.save(user);
            })
            .orElseThrow(() -> new ResourceNotFoundException("User Not found"));
    }

    @Override
    public void delete(String email) throws ResourceNotFoundException {
        Optional
            .of(isPresent(email))
            .get()
            .map(u -> {
                userRepository.delete(u);
                return null;
            })
            .orElseThrow(() -> new ResourceNotFoundException("User Not found"));
    }

    @Override public User getUser(String email) throws ResourceNotFoundException {
        return Optional
            .of(isPresent(email))
            .get()
            .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

    }

}
