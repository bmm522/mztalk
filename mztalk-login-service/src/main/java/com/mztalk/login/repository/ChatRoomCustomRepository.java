package com.mztalk.login.repository;


import com.mztalk.login.domain.entity.Chatroom;

import java.util.List;

public interface ChatRoomCustomRepository {


    List<Chatroom> getChatRoomList(long userNo);

    long deleteChatRoom(long fromUserNo, long toUserNo);
}
