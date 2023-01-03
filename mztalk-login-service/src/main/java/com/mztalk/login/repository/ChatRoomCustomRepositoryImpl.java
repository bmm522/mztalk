package com.mztalk.login.repository;

import com.mztalk.login.domain.entity.Chatroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ChatRoomCustomRepositoryImpl implements ChatRoomCustomRepository{

    @Autowired
    EntityManager entityManager;


    @Override
    public List<Chatroom> getChatRoomListOfAuction(long id) {
        return entityManager.createQuery("SELECT c FROM Chatroom c WHERE c.fromUser.id= :id AND c.status = 'Y' AND c.serviceName = 'auction'", Chatroom.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Chatroom> getChatRoomListOfBung(long id) {
        return entityManager.createQuery("SELECT c FROM Chatroom c WHERE c.fromUser.id= :id AND c.status = 'Y' AND c.serviceName = 'bung'", Chatroom.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public long deleteChatRoom(long fromUserNo, long toUserNo) {
        return entityManager.createQuery("UPDATE Chatroom c SET c.status = 'N' WHERE c.fromUser.id=:fromUserNo AND c.toUserNo = :toUserNo")
                .setParameter("fromUserNo", fromUserNo)
                .setParameter("toUserNo", toUserNo)
                .executeUpdate();
    }

    @Override
    public long checkData(long fromUserId, long toUserId) {
        return (Long)entityManager.createQuery("SELECT count(*)  FROM Chatroom  c WHERE c.fromUser.id=:fromUserId AND c.toUserNo=:toUserId AND c.serviceName='bung'")
                .setParameter("fromUserId", fromUserId)
                .setParameter("toUserId", toUserId)
                .getSingleResult();
    }
}
