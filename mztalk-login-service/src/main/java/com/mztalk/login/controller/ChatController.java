package com.mztalk.login.controller;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.request.ChatOfUserNicknameRequestDto;
import com.mztalk.login.domain.dto.request.ChatOfUserNoRequestDto;
import com.mztalk.login.domain.dto.response.ChatResultResponseDto;
import com.mztalk.login.service.InsertChatService;
import com.mztalk.login.service.SelectChatService;
import com.mztalk.login.service.UpdateChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Api(tags = "chat-api")
public class ChatController {


    private final InsertChatService insertChatService;

    private final SelectChatService selectChatService;

    private final UpdateChatService updateChatService;


    @PostMapping("/chat/userNo")
    @ApiOperation(value = "대화 신청(번호)", notes = "유저 번호로 해당 두 유저의 채팅방이 개설됩니다.")
    public long requestChatOfUserNo(@RequestBody ChatOfUserNoRequestDto chatOfUserNoRequestDto){
        return insertChatService.requestChatOfUserNo(chatOfUserNoRequestDto);
    }

    @PostMapping("/chat/nickname")
    @ApiOperation(value = "대화 신청(닉네임, 백)", notes = "유저 닉네임으로 해당 두 유저의 채팅방이 개설됩니다.")
    public ChatResultResponseDto requestChatOfUserNickname(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto){
        return insertChatService.requestChatOfUserNickname(chatOfUserNicknameRequestDto);
    }

    @ResponseBody
    @PostMapping("/chat/front-nickname")
    @ApiOperation(value = "대화 신청(닉네임, 프론트)", notes = "유저 닉네임으로 해당 두 유저의 채팅방이 개설됩니다.")
    public ChatResultResponseDto requestChatOfFrontUserNickname(@RequestBody ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto){
        return insertChatService.requestChatOfUserNickname(chatOfUserNicknameRequestDto);
    }



    @GetMapping("/chat/nickname")
    @ApiOperation(value = "")
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

    @GetMapping("/chat/story")
    public Result<?> getChatRoomListOfStroy(@RequestParam("userNo")long userNo){
        return selectChatService.getChatRoomListOfStroy(userNo);
    }

    @PatchMapping("/chat")
    public long deleteChatRoom(@RequestParam("fromUserNo") long fromUserNo, @RequestParam("toUserNo")long toUserNo){
        return updateChatService.deleteChatRoom(fromUserNo, toUserNo);
    }
}
