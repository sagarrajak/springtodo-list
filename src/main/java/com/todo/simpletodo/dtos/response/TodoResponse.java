package com.todo.simpletodo.dtos.response;

import com.todo.simpletodo.entity.Todo;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoResponse  {

//    private Date createdAt;
//
//    private Date updatedAt;

    private boolean isDone;

    private String title;

    private String description;

    public TodoResponse(Todo todo) {
//        this.createdAt = todo.getCreatedAt();
//        this.updatedAt = todo.getUpdatedAt();
        this.isDone = todo.isDone();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
    }
}
