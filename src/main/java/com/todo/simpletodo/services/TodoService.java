package com.todo.simpletodo.services;

import com.todo.simpletodo.entity.Todo;
import com.todo.simpletodo.entity.User;
import com.todo.simpletodo.repository.TodoRepository;
import com.todo.simpletodo.dtos.request.ModifyTodoRequestDto;
import com.todo.simpletodo.dtos.request.TodoRequestDto;
import com.todo.simpletodo.utils.UpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UpdateService<Todo> updateService;
    public Todo saveTodo(TodoRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        System.out.println(user);
        Todo todo = Todo.builder().title(dto.getTitle()).user(user).description(dto.getDescription()).build();
        System.out.println(dto);
        return todoRepository.save(todo);
    }

    public Page<Todo> findAllTodo(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return todoRepository.findByUserId(user.getId(), pageable);
    }

    public List<Todo> markTodoComplete(Integer[] items) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Todo> todos = todoRepository.findAllById(List.of(items));
        todos.forEach(itms -> itms.setDone(true));
        return  todoRepository.saveAll(todos);
    }

    public Todo modifyTodo(ModifyTodoRequestDto todo) {
        System.out.println(todo);
        Optional<Todo> foundTodo = todoRepository.findById(todo.getId());
        if (foundTodo.isPresent()) {

            Todo found = foundTodo.get();
            Todo modifedTodo = Todo.builder()
                        .description(todo.getDescription())
                        .title(todo.getTitle())
                        .isDone(found.isDone())
                        .build();

            try {
                Todo updatedTodo = updateService.updateObject(found, modifedTodo);
                System.out.println(updatedTodo);
                return this.todoRepository.save(updatedTodo);
            }
            catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw  new RuntimeException("Todo not found!");
        }
    }
}
