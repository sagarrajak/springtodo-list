package com.todo.simpletodo.services.impl;

import com.todo.simpletodo.services.JwtService;
import com.todo.simpletodo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String extractUserName(String token) {
        return JwtUtil.extractUsername(token);
    }

    @Override
    public String generateToken(String username) {
        return JwtUtil.generateToken(username);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = userDetails.getUsername();
        String extractUserName = extractUserName(token);
        return username.equals(extractUserName) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return JwtUtil.extractExpiration(token).before(new Date());
    }
}
