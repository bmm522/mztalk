package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.request.ChatOfUserNicknameRequestDto;
import com.mztalk.login.domain.dto.request.ChatOfUserNoRequestDto;
import com.mztalk.login.domain.dto.response.ChatResultResponseDto;
import com.mztalk.login.service.InsertChatService;
import com.mztalk.login.service.SelectChatService;
import com.mztalk.login.service.UpdateChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class ChatController {


    private final InsertChatService insertChatService;

    private final SelectChatService selectChatService;

    private final UpdateChatService updateChatService;


    @PostMapping("/chat/userNo")
    public long requestChatOfUserNo(@RequestBody ChatOfUserNoRequestDto chatOfUserNoRequestDto){
        return insertChatService.requestChatOfUserNo(chatOfUserNoRequestDto);
    }

    @PostMapping("/chat/nickname")
    public ChatResultResponseDto requestChatOfUserNickname(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto){
        return insertChatService.requestChatOfUserNickname(chatOfUserNicknameRequestDto);
    }

    @ResponseBody
    @PostMapping("/chat/front-nickname")
    public ChatResultResponseDto requestChatOfFrontUserNickname(@RequestBody ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto){
        return insertChatService.requestChatOfUserNickname(chatOfUserNicknameRequestDto);
    }



    @GetMapping("/chat/nickname")
    public ChatResultResponseDto requestChatOfUserNicknameWhenBack(@RequestParam("serviceName")String serviceName, @RequestParam("fromUserNickname")String fromUserNickname, @RequestParam("toUserNickname")String toUserNickname){
        return insertChatService.requestChatOfUserNickname(new ChatOfUserNicknameRequestDto(serviceName, fromUserNickname, toUserNickname));
    }

    @GetMapping("/chat/auction")
    public Result<?> getChatRoomListOfAuction(@RequestParam("userNo")long userNo){
        return selectChatService.getChatRoomListOfAuction(userNo);
    }

    @GetMapping("/chat/bung")
    public Result<?> getChatRoomListOfBung(@RequestParam("userNo")long userNo){
        return selectChatService.getChatRoomListOfBung(userNo);
    }

    @PatchMapping("/chat")
    public long deleteChatRoom(@RequestParam("fromUserNo") long fromUserNo, @RequestParam("toUserNo")long toUserNo){
        return updateChatService.deleteChatRoom(fromUserNo, toUserNo);
    }
}
