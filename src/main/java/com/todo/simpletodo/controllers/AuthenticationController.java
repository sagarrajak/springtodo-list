package com.todo.simpletodo.controllers;

import com.todo.simpletodo.dtos.request.SigninRequestDto;
import com.todo.simpletodo.dtos.request.SignupRequestDto;
import com.todo.simpletodo.dtos.response.JwtAuthenticationResponse;
import com.todo.simpletodo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignupRequestDto request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SigninRequestDto requestDto) {
        return authenticationService.signIn(requestDto);
    }

}
