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
        return new Result<>(getChatResponseDtoList(chatroomRepository.getChatRoomListOfAuction(userNo)));
    }

    public List<ChatResponseDto> getChatResponseDtoList(List<Chatroom> chatroomList){
        List<ChatResponseDto> chatResponseDtoList = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");

        for(Chatroom chatroom : chatroomList){
            String imageUrl = "";
            try{
                ResponseEntity<String> response = new RestTemplate().exchange(
                        "http://localhost:8000/resource/main-image?bNo=" + chatroom.getToUserNo() + "&serviceName=story",
                        HttpMethod.GET,
                        new HttpEntity<String>(headers),
                        String.class
                );
                JSONObject jsonObject = new JSONObject(response.getBody());
                JSONObject jsonData = jsonObject.getJSONObject("data");
                imageUrl =  jsonData.getString("imageUrl");
            } catch (Exception e){
                imageUrl = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png";
            }

            chatResponseDtoList.add(new ChatResponseDto(chatroom, imageUrl));
        }
        return chatResponseDtoList;

    }
}
