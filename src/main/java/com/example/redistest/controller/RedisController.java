package com.example.redistest.controller;

import com.example.redistest.dto.ChatMessage;
import com.example.redistest.service.RedisPubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisController {
    private final RedisPubService redisPubService;
    @PostMapping("/test")
    public void test(@RequestBody ChatMessage chatMessage) {
        redisPubService.sendMessage(chatMessage);
    }
}