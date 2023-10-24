package com.example.UsedTrade.auth.service;


import com.example.UsedTrade.auth.configure.auth.TokenProvider;
import com.example.UsedTrade.auth.entity.UserEntity;
import com.example.UsedTrade.auth.model.request.JoinRequest;
import com.example.UsedTrade.auth.model.request.LoginRequest;
import com.example.UsedTrade.auth.repository.UserRepository;
import com.example.UsedTrade.auth.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public void join(JoinRequest request) {
        userRepository.save(UserConverter.to(request));
    }
    public boolean login(LoginRequest request) {
        UserEntity entity =userRepository.findByEmail(request.getEmail()).orElseThrow();

        String accessToken = tokenProvider.createToken(entity);
        System.out.println("🐶 : "+accessToken);
        if(entity == null){
            return false;
        }

        if(entity.getPassword().equals(request.getPassword())){
            return true;
        }else{
            return false;
        }

    }
}
