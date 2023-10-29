package com.example.UsedTrade.auth.configure;

import com.example.UsedTrade.auth.configure.auth.JwtFilter;
import com.example.UsedTrade.auth.configure.auth.TokenProvider;
import com.example.UsedTrade.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider,userRepository);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }


}