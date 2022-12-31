package com.mztalk.main.domain.message;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/pubsub")
@RestController
public class PubSubController {

    // topic에 메시지 발행을 기다리는 Listner
    private final RedisMessageListenerContainer redisMessageListener;

    // 발행자
    private final RedisPublisher redisPublisher;

    // 구독자
    private final RedisSubscriber redisSubscriber;

    // topic 이름으로 topic정보를 가져와 메시지를 발송할 수 있도록 Map에 저장

    private Map<String, ChannelTopic> channels;


}