package com.todo.simpletodo.controllers;

import com.todo.simpletodo.entity.Todo;
import com.todo.simpletodo.dtos.request.ModifyTodoRequestDto;
import com.todo.simpletodo.dtos.request.TodoRequestDto;
import com.todo.simpletodo.dtos.response.TodoResponse;
import com.todo.simpletodo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping()
    public ResponseEntity<TodoResponse> addTodo(@RequestBody TodoRequestDto dto) {
        TodoResponse todoResponse = new TodoResponse(this.todoService.saveTodo(dto));
        return new ResponseEntity<>(todoResponse, HttpStatus.CREATED);
    }


    @GetMapping("paginated")
    public ResponseEntity<Page<TodoResponse>> getAllTodo(Pageable pageable) {
        Page<Todo> allTodo = this.todoService.findAllTodo(pageable);
        return new ResponseEntity<>(allTodo.map(TodoResponse::new), HttpStatus.OK);
    }

    @PutMapping("mark-done")
    public ResponseEntity<TodoResponse[]> markTodoComplete(@RequestParam(required = true) Integer[] items) {
        List<Todo> todos = this.todoService.markTodoComplete(items);
        return new ResponseEntity<>(todos.stream().map(TodoResponse::new).toArray(TodoResponse[]::new), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<TodoResponse> modifyTodo(@RequestBody ModifyTodoRequestDto todo) {
         return new ResponseEntity<>(new TodoResponse(this.todoService.modifyTodo(todo)), HttpStatus.OK);
    }

}
