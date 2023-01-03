package com.mztalk.login.repository;

import com.mztalk.login.domain.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long>, ChatRoomCustomRepository {
}
