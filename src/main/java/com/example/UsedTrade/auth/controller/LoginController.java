package com.example.UsedTrade.auth.controller;

import com.example.UsedTrade.auth.model.Token;
import com.example.UsedTrade.auth.model.request.JoinRequest;
import com.example.UsedTrade.auth.model.request.LoginRequest;
import com.example.UsedTrade.auth.service.UserService;
import com.example.UsedTrade.support.ApiResponse;
import com.example.UsedTrade.support.ApiResponseGenerator;
import com.example.UsedTrade.support.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@RequestMapping("/api/v1/user")
public class LoginController
{

    private final UserService userService;

    @PostMapping("/join")
    public ApiResponse<ApiResponse.SuccessBody<Void>> join(@RequestBody JoinRequest request) {
        userService.join(request);
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.SUCCESS);
    }

    @PostMapping("/login")
    public ApiResponse<ApiResponse.SuccessBody<Token>> login(@RequestBody LoginRequest request) {
        Token token = userService.login(request);
        return  ApiResponseGenerator.success(token,HttpStatus.OK, MessageCode.SUCCESS);

    }

}
