package com.mztalk.loginservice.chat.repository;

import com.mztalk.loginservice.chat.repository.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long>, ChatRoomCustomRepository {
}
