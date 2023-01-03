package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.request.ChatOfUserNicknameRequestDto;
import com.mztalk.login.domain.dto.request.ChatOfUserNoRequestDto;
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
    public long requestChatOfUserNickname(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto){
        return insertChatService.requestChatOfUserNickname(chatOfUserNicknameRequestDto);
    }

    @GetMapping("/chat/nickname")
    public long requestChatOfUserNicknameWhenBack(@RequestParam("serviceName")String serviceName, @RequestParam("fromUserNickname")String fromUserNickname, @RequestParam("toUserNickname")String toUserNickname){
        return insertChatService.requestChatOfUserNickname(new ChatOfUserNicknameRequestDto(serviceName, fromUserNickname, toUserNickname));
    }

    @GetMapping("/chat")
    public Result<?> getChatRoomList(@RequestParam("userNo")long userNo){
        return selectChatService.getChatRoomList(userNo);
    }

    @PatchMapping("/chat")
    public long deleteChatRoom(@RequestParam("fromUserNo") long fromUserNo, @RequestParam("toUserNo")long toUserNo){
        return updateChatService.deleteChatRoom(fromUserNo, toUserNo);
    }
}
