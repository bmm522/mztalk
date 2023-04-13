package com.mztalk.loginservice.chat.application;

import com.mztalk.loginservice.global.dto.Result;
import com.mztalk.loginservice.chat.repository.entity.Chatroom;
import com.mztalk.loginservice.chat.repository.ChatroomRepository;
import com.mztalk.loginservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SelectChatServiceImpl implements SelectChatService {

    private final ChatroomRepository chatroomRepository;

    private final UserRepository userRepository;
    @Override
    public Result<?> getChatRoomListOfAuction(long userNo) {
        return new Result<>(getChatResponseDtoList(chatroomRepository.getChatRoomListOfAuction(userNo)));
    }

    @Override
    public Result<?> getChatRoomListOfBung(long userNo) {
        return new Result<>(getChatResponseDtoList(chatroomRepository.getChatRoomListOfBung(userNo)));
    }

    @Override
    public Result<?> getChatRoomListOfStroy(long userNo) {
        return new Result<>(getChatResponseDtoList(chatroomRepository.getChatRoomListOfStroy(userNo)));
    }

    public List<ChatResponseDto> getChatResponseDtoList(List<Chatroom> chatroomList){
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for(Chatroom chatroom : chatroomList){
            chatResponseDtoList.add(new ChatResponseDto(chatroom));
        }
        return chatResponseDtoList;

    }
}
