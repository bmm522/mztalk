package com.mztalk.login.service;

import com.mztalk.login.domain.dto.Result;

public interface SelectChatService {
    Result<?> getChatRoomListOfAuction(long userNo);

    Result<?> getChatRoomListOfBung(long userNo);

    Result<?> getChatRoomListOfStroy(long userNo);
}
