package com.example.UsedTrade.chat.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatEntity {
    // 메시지  타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK
    }



    private MessageType type; // 메시지 타입
    private String roomId; // 방 번호
    private String sender; // 채팅을 보낸 사람
    private String message; // 메시지
    private String time; // 채팅 발송 시간간

    public ChatEntity() {

    }

    public ChatEntity(MessageType type,String roomId,String sender, String message,String time){
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.time = time;

    }
}