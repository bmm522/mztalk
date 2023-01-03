package com.mztalk.login.repository;


import com.mztalk.login.domain.entity.Chatroom;

import java.util.List;

public interface ChatRoomCustomRepository {


    List<Chatroom> getChatRoomListOfAuction(long userNo);

    List<Chatroom> getChatRoomListOfBung(long userNo);

    long deleteChatRoom(long fromUserNo, long toUserNo);

    long checkData(long fromUserId, long toUserId);
}
