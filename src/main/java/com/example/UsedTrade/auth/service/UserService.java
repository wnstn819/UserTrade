package com.example.UsedTrade.auth.service;


import com.example.UsedTrade.auth.entity.UserEntity;
import com.example.UsedTrade.auth.model.request.JoinRequest;
import com.example.UsedTrade.auth.model.request.LoginRequest;
import com.example.UsedTrade.auth.repository.UserRepository;
import com.example.UsedTrade.auth.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;

    public void join(JoinRequest request) {
        userRepository.save(UserConverter.to(request));
    }
    public boolean login(LoginRequest request) {
        Optional<UserEntity> entity =userRepository.findByEmail(request.getEmail());
        if(entity.isEmpty()){
            return false;
        }

        if(entity.get().getPassword().equals(request.getPassword())){
            return true;
        }else{
            return false;
        }

    }
}
