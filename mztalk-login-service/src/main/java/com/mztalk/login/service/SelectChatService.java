package com.mztalk.login.service;

import com.mztalk.login.domain.dto.Result;

public interface SelectChatService {
    Result<?> getChatRoomList(long userNo);
}
