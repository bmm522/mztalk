package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.request.ChatRequestDto;
import com.mztalk.login.domain.entity.Chatroom;
import com.mztalk.login.repository.ChatroomRepository;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.InsertChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InsertChatServiceImpl implements InsertChatService {

    private final ChatroomRepository chatroomRepository;

    private final UserRepository userRepository;
    @Override
    public long requestChat(ChatRequestDto chatRequestDto) {
        Chatroom chatroom = chatRequestDto.toEntity
                (userRepository.findById(chatRequestDto.getFromUserId()).orElseThrow(()->new NullPointerException("Not Found User")));
        return chatroomRepository.save(chatroom).getChatId();
    }
}
