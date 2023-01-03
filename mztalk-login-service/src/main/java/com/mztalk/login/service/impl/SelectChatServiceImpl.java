package com.mztalk.login.service.impl;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.response.ChatResponseDto;
import com.mztalk.login.domain.entity.Chatroom;
import com.mztalk.login.repository.ChatroomRepository;
import com.mztalk.login.repository.UserRepository;
import com.mztalk.login.service.SelectChatService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public List<ChatResponseDto> getChatResponseDtoList(List<Chatroom> chatroomList){
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        for(Chatroom chatroom : chatroomList){
            chatResponseDtoList.add(new ChatResponseDto(chatroom));
        }
        return chatResponseDtoList;

    }
}
