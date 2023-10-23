package com.example.UsedTrade.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.UsedTrade.auth.entity.UserEntity;

import java.util.*;


public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String Email);

}
