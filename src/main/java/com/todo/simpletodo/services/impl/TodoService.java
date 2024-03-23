//package com.todo.simpletodo.services.impl;
//
//import com.todo.simpletodo.entity.Todo;
//import com.todo.simpletodo.repository.TodoRepository;
//import com.todo.simpletodo.request.TodoRequestDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class TodoService {
//    private final TodoRepository repository;
//
//    public Todo saveTodo(TodoRequestDto dto) {
//        Todo build = Todo.builder()
//                .title(dto.getTitle())
//                .description(dto.getDescription())
//                .createdAt(dto.getCreatedAt())
//                .updatedAt(dto.getCreatedAt())
//                .build();
//        return repository.save(build);
//    }
//
//}
