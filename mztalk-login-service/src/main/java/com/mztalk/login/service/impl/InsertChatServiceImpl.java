package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.request.ChatOfUserNicknameRequestDto;
import com.mztalk.login.domain.dto.request.ChatOfUserNoRequestDto;
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
    public long requestChatOfUserNo(ChatOfUserNoRequestDto chatOfUserNoRequestDto) {
        Chatroom chatroom = chatOfUserNoRequestDto.toEntity
                (userRepository.findById(chatOfUserNoRequestDto.getFromUserId()).orElseThrow(()->new NullPointerException("Not Found User")));
        return chatroomRepository.save(chatroom).getChatId();
    }

    @Override
    public long requestChatOfUserNickname(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto) {
        System.out.println(chatOfUserNicknameRequestDto.getToUserNickname());
        Chatroom chatroom = chatOfUserNicknameRequestDto.toEntity
                (userRepository.findByNickname(chatOfUserNicknameRequestDto.getFromUserNickname())
                ,userRepository.findByNickname(chatOfUserNicknameRequestDto.getToUserNickname()).getId());
        return chatroomRepository.save(chatroom).getChatId();
    }
}
