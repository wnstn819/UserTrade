package com.example.UsedTrade.url.controller;


import com.example.UsedTrade.support.ApiResponse;
import com.example.UsedTrade.support.ApiResponseGenerator;
import com.example.UsedTrade.support.MessageCode;
import com.example.UsedTrade.url.model.request.LongUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
public class UrlController {

    @PostMapping("/shorten")
    public ApiResponse<ApiResponse.SuccessBody<Void>> join(@RequestBody LongUrlRequest request) throws Exception {
        System.out.println("hello");
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.SUCCESS);
    }

    @GetMapping("{shortUrl}")
    public ApiResponse<ApiResponse.SuccessBody<Void>> login(@PathVariable String shortUrl) {
        System.out.println("hi");
        return  ApiResponseGenerator.success(HttpStatus.OK, MessageCode.SUCCESS);

    }
}
