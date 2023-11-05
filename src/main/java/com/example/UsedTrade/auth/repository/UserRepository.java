package com.example.UsedTrade.auth.repository;

import com.example.UsedTrade.auth.entity.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.UsedTrade.auth.entity.UserEntity;

import java.util.*;


public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String Email);

    Optional<UserEntity> findByNickname(String nickname);

    Optional<UserEntity> findByRefreshToken(String refreshToken);

    Optional<UserEntity> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

}
