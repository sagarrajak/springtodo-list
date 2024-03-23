package com.todo.simpletodo.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SigninRequestDto {
    @NotBlank()
    String username;
    @NotBlank()
    String password;

}
