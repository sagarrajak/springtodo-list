package com.todo.simpletodo.services;

import com.todo.simpletodo.request.SigninRequestDto;
import com.todo.simpletodo.request.SignupRequestDto;
import com.todo.simpletodo.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignupRequestDto dto);

    JwtAuthenticationResponse signIn(SigninRequestDto dto);
}
