package com.mztalk.login.repository;

import com.mztalk.login.domain.entity.Chatroom;
import com.mztalk.login.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class UserCustomRepositoryImpl implements UserCustomRepository{

    @Autowired
    EntityManager entityManager;

    public int updateRoleChangeToVip(Long id) {
        return entityManager.createQuery("UPDATE User u SET u.role = 'ROLE_VIP' WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public int updateRoleChangeToUser(Long id) {
        return entityManager.createQuery("UPDATE User u SET u.role = 'ROLE_USER' WHERE u.id =: id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public int changedPassword(String newPassword, long id) {
        return entityManager.createQuery("UPDATE User u SET u.password = :newPassword WHERE u.id = :id")
                .setParameter("newPassword", newPassword)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public String findByPasswordWithId(long id) {
        return entityManager.createQuery("SELECT u.password FROM User u WHERE u.id = :id",String.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public int updateNickname(long id, String nickname) {
        return entityManager.createQuery("UPDATE User u SET u.nickname = :nickname WHERE u.id=:id")
                .setParameter("nickname", nickname)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public int updateEmail(long id, String email) {
        return entityManager.createQuery("UPDATE User u SET u.email = :email WHERE u.id = :id")
                .setParameter("email", email)
                .setParameter("id" , id)
                .executeUpdate();
    }

    @Override
    public int updateReportCount(long id) {
        return entityManager.createQuery("UPDATE User u SET u.reportCount = u.reportCount + 1 WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<User> getMaliciousUser() {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.reportCount >= 3 AND u.status ='Y'", User.class)
                .getResultList();
    }

    @Override
    public long updateUserStatus(String status, long id) {
        return entityManager.createQuery("UPDATE User u SET u.status=:status WHERE u.id = :id")
                .setParameter("status", status)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public long findToUserIdByToUserNickname(String fromUserNickname) {
        return entityManager.createQuery("SELECT u.id FROM User u WHERE u.nickname = :fromUserNickname")
                .setParameter("fromUserNickname", fromUserNickname)
                .getFirstResult();
    }


    public  void commit(){
        entityManager.flush();
        entityManager.clear();
    }


}
