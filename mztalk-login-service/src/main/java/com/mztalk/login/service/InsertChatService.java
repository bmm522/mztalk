package com.mztalk.login.service;

import com.mztalk.login.domain.dto.request.ChatOfUserNicknameRequestDto;
import com.mztalk.login.domain.dto.request.ChatOfUserNoRequestDto;
import com.mztalk.login.domain.dto.response.ChatResultResponseDto;

public interface InsertChatService {
    long requestChatOfUserNo(ChatOfUserNoRequestDto chatOfUserNoRequestDto);

    ChatResultResponseDto requestChatOfUserNickname(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto);
}
