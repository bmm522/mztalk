package com.mztalk.loginservice.chat.application;

public interface InsertChatService {
    long requestChatOfUserNo(ChatOfUserNoRequestDto chatOfUserNoRequestDto);

    ChatResultResponseDto requestChatOfUserNickname(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto);
}
