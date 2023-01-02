package com.mztalk.login.service;

import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.request.ChatRequestDto;

public interface InsertChatService {
    long requestChat(ChatRequestDto chatRequestDto);
}
