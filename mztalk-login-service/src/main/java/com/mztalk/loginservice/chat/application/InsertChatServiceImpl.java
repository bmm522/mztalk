package com.mztalk.loginservice.chat.application;

import com.mztalk.loginservice.chat.repository.entity.Chatroom;
import com.mztalk.loginservice.chat.repository.ChatroomRepository;
import com.mztalk.loginservice.user.repository.UserRepository;
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
    public ChatResultResponseDto requestChatOfUserNickname(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto) {
        String serviceName = chatOfUserNicknameRequestDto.getServiceName();

        if (checkData(chatOfUserNicknameRequestDto, serviceName) == 0L) {
            Chatroom chatroom1 = getChatroomFrom(chatOfUserNicknameRequestDto);
            Chatroom chatroom2 = getChatroomTo(chatOfUserNicknameRequestDto);

            new ChatResultResponseDto(chatroomRepository.save(chatroom1).getChatId());
            return new ChatResultResponseDto(chatroomRepository.save(chatroom2).getChatId());
        }

        return new ChatResultResponseDto(0L);
    }

    private long checkData(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto, String serviceName){
        return chatroomRepository.checkData(userRepository.findByNickname(chatOfUserNicknameRequestDto.getFromUserNickname()).getId(),
                userRepository.findByNickname(chatOfUserNicknameRequestDto.getToUserNickname()).getId(), serviceName);
    }

    private Chatroom getChatroomFrom(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto){
        return chatOfUserNicknameRequestDto.toEntity
                (userRepository.findByNickname(chatOfUserNicknameRequestDto.getFromUserNickname())
                        ,userRepository.findByNickname(chatOfUserNicknameRequestDto.getToUserNickname()).getId());
    }

    private Chatroom getChatroomTo(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto){
        return chatOfUserNicknameRequestDto.toEntity
                (userRepository.findByNickname(chatOfUserNicknameRequestDto.getToUserNickname())
                        ,userRepository.findByNickname(chatOfUserNicknameRequestDto.getFromUserNickname()).getId());
    }




}
