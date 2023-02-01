package com.example.redistest.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.redistest.service.RedisSubService;
import com.example.redistest.dto.ChatMessage;
@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    //redisTemplate 설정
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        System.out.println("이것들을 언제 사용하는걸까1111111");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>(); // 그저 repository 같은 느낌???
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // 이걸 안해줘도 문제가 없지만 사람이 보기 편하게 만들어주기 위해 이걸 씀 + 안에 들어가 있는 변수는 직렬화의 방식???
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatMessage.class)); // 이걸 안해줘도 문제가 없지만 사람이 보기 편하게 만들어주기 위해 이걸 씀 + 안에 들어가 있는 변수는 직렬화의 방식???
        return redisTemplate;
    }

    //리스너어댑터 설정
    @Bean
    MessageListenerAdapter messageListenerAdapter() {
        System.out.println("이것들을 언제 사용하는걸까222222222222");
        return new MessageListenerAdapter(new RedisSubService());
    }

    //컨테이너 설정
    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) { // 왜 public이 안붙는거지??
        System.out.println("이것들을 언제 사용하는걸까333333333333333");
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter(), topic());
        return container;
    }

    //pub/sub 토픽 설정
    @Bean
    ChannelTopic topic() {
        System.out.println("이것들을 언제 사용하는걸까444444444444");
        return new ChannelTopic("topic1");
    }
}