package com.mztalk.login.service;

import com.mztalk.login.domain.dto.request.ChatOfUserNicknameRequestDto;
import com.mztalk.login.domain.dto.request.ChatOfUserNoRequestDto;

public interface InsertChatService {
    long requestChatOfUserNo(ChatOfUserNoRequestDto chatOfUserNoRequestDto);

    long requestChatOfUserNickname(ChatOfUserNicknameRequestDto chatOfUserNicknameRequestDto);
}
