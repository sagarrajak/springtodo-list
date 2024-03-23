package com.todo.simpletodo.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ModifyTodoRequestDto {
    String title;

    String description;

    @NotNull
    Integer id;
}
