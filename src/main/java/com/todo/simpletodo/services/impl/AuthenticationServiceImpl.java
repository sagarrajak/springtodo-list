package com.todo.simpletodo.services.impl;

import com.todo.simpletodo.entity.Role;
import com.todo.simpletodo.entity.User;
import com.todo.simpletodo.repository.UserRepository;
import com.todo.simpletodo.request.SigninRequestDto;
import com.todo.simpletodo.request.SignupRequestDto;
import com.todo.simpletodo.response.JwtAuthenticationResponse;
import com.todo.simpletodo.services.AuthenticationService;
import com.todo.simpletodo.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignupRequestDto dto) {
        Optional<User> foundUser = this.userRepository.findByEmail(dto.getEmail());
        if (foundUser.isPresent()) {
            throw new IllegalArgumentException("User already present, try to sign in!");
        }
        User user = User.builder().firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .isEnabled(true)
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user.getUsername());
        return JwtAuthenticationResponse.builder().token(token).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SigninRequestDto dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        var user = this.userRepository.findByEmail(dto.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password!"));
        var jwt = jwtService.generateToken(user.getUsername());
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
