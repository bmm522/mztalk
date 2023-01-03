package com.mztalk.login.service;

import com.mztalk.login.domain.dto.Result;

public interface UpdateChatService {
    long deleteChatRoom(long fromUserNo, long toUserNo);
}
