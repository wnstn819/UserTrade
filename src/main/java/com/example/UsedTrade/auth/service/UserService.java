package com.example.UsedTrade.auth.service;


import com.example.UsedTrade.auth.configure.auth.TokenProvider;
import com.example.UsedTrade.auth.entity.UserEntity;
import com.example.UsedTrade.auth.model.Token;
import com.example.UsedTrade.auth.model.request.JoinRequest;
import com.example.UsedTrade.auth.model.request.LoginRequest;
import com.example.UsedTrade.auth.repository.UserRepository;
import com.example.UsedTrade.auth.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class UserService {

    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Value("${spring.auth.jwt.refresh_time}")
    private Long REFRESH_TOKEN_EXPIRE_TIME;

    public void join(JoinRequest request) {
        userRepository.save(UserConverter.to(request));
    }
    public Token login(LoginRequest request) {
        UserEntity entity =userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
        String username = authenticationToken.getName();
        Token token = tokenProvider.generateToken(entity);
        redisTemplate.opsForValue().set(
                username,
                token.getRefreshToken(),
                REFRESH_TOKEN_EXPIRE_TIME,
                TimeUnit.MILLISECONDS
        );

        return token;

    }

    public String reissue(String token){
        Authentication authentication = tokenProvider.getAuthentication(token);
        String refreshToken = (String) redisTemplate.opsForValue().get(authentication.getName());

        return refreshToken;
    }
}


