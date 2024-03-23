package com.todo.simpletodo.services;

import com.todo.simpletodo.dtos.request.SigninRequestDto;
import com.todo.simpletodo.dtos.request.SignupRequestDto;
import com.todo.simpletodo.dtos.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignupRequestDto dto);

    JwtAuthenticationResponse signIn(SigninRequestDto dto);
}
