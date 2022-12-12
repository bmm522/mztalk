package com.mztalk.login.repository;

import com.mztalk.login.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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


    public  void commit(){
        entityManager.flush();
        entityManager.clear();
    }


}
