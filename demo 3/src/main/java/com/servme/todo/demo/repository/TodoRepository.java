package com.servme.todo.demo.repository;

import com.servme.todo.demo.model.Todo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findByUserId(Long userId);
    List<Todo> findAllByUserId(Long userId);
    Optional<Todo> findByIdAndUserId(Long id, Long userId);
}
