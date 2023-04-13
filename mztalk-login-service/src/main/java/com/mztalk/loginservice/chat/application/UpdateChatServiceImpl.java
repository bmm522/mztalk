package com.mztalk.loginservice.chat.application;

import com.mztalk.loginservice.chat.repository.ChatroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UpdateChatServiceImpl implements UpdateChatService {


    private final ChatroomRepository chatroomRepository;
    @Override
    public long deleteChatRoom(long fromUserNo, long toUserNo) {
        return chatroomRepository.deleteChatRoom(fromUserNo, toUserNo);
    }
}
