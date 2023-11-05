package com.example.UsedTrade.chat.service;

import com.example.UsedTrade.chat.entity.ChatRoomEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


import java.io.IOException;
import java.util.*;

@Slf4j
@Data
@Service
public class ChatService {
    private final ObjectMapper mapper;
    private Map<String, ChatRoomEntity> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomEntity> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomEntity findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    public ChatRoomEntity createRoom(String name) {
        String roomId = UUID.randomUUID().toString(); // 랜덤한 방 아이디 생성

        // Builder 를 이용해서 ChatRoom 을 Building
        ChatRoomEntity room = ChatRoomEntity.builder()
                .roomId(roomId)
                .name(name)
                .build();

        chatRooms.put(roomId, room); // 랜덤 아이디와 room 정보를 Map 에 저장
        return room;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}