package com.example.UsedTrade.auth.util;


import com.example.UsedTrade.auth.entity.UserEntity;
import com.example.UsedTrade.auth.model.request.JoinRequest;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class UserConverter {

    public static UserEntity to(JoinRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
