package com.example.redistest.service;

import com.example.redistest.dto.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisSubService implements MessageListener {
    public static List<String> messageList = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            System.out.println("--------------------------------------");
            System.out.println(message);
            System.out.println(ChatMessage.class);
            System.out.println(mapper.readValue(message.getBody(), ChatMessage.class));
            System.out.println("--------------------------------------");
            ChatMessage chatMessage = mapper.readValue(message.getBody(), ChatMessage.class); // ObjectMapper를 이용해서 변수 값에 넣어 줄 수 있음
            System.out.println("1111111111111111111111111111111111111111111111");
            System.out.println(chatMessage);
            System.out.println("1111111111111111111111111111111111111111111111");
            messageList.add(message.toString());

            System.out.println("받은 메시지 = " + message.toString());
            System.out.println("chatMessage.getSender() = " + chatMessage.getSender());
            System.out.println("chatMessage.getContext() = " + chatMessage.getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
