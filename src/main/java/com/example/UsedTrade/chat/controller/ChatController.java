package com.example.UsedTrade.chat.controller;

import com.example.UsedTrade.chat.entity.ChatRoomEntity;
import com.example.UsedTrade.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService service;

    @PostMapping
    public ChatRoomEntity createRoom(@RequestParam String name){
        return service.createRoom(name);
    }

    @GetMapping
    public List<ChatRoomEntity> findAllRooms(){
        return service.findAllRoom();
    }
}