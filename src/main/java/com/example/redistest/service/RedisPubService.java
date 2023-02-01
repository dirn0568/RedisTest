package com.example.redistest.service;


import com.example.redistest.dto.ChatMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPubService {
    private final RedisTemplate<String, Object> redisTemplate;
    public void sendMessage(ChatMessage chatMessage) {
        System.out.println("이것들을 언제 사용하는걸까7");
        redisTemplate.convertAndSend("topic1", chatMessage); // 뿌릴때 채널을 맞춰준건가??? 채널이 안맞춰져 있으면 안감
    }
}
