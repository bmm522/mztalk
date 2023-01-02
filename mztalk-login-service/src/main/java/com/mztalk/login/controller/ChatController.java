package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.request.ChatRequestDto;
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


    @PostMapping("/chat")
    public long requestChat(@RequestBody ChatRequestDto chatRequestDto){
        return insertChatService.requestChat(chatRequestDto);
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
