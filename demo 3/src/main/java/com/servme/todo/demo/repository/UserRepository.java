package com.servme.todo.demo.repository;

import com.servme.todo.demo.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    Optional<User> findByEmail(String email);


    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findById(Long userId);


    Boolean existsByEmail(String email);

}
