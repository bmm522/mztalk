package com.mztalk.loginservice.chat.application;

import com.mztalk.loginservice.global.dto.Result;

public interface SelectChatService {
    Result<?> getChatRoomListOfAuction(long userNo);

    Result<?> getChatRoomListOfBung(long userNo);

    Result<?> getChatRoomListOfStroy(long userNo);
}
