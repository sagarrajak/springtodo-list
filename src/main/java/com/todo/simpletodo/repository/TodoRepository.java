package com.todo.simpletodo.repository;

import com.todo.simpletodo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    Page<Todo> findByUserId(Integer id, Pageable pageRequest);
}
