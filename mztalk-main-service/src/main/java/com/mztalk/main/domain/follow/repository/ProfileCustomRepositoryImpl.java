package com.mztalk.main.domain.follow.repository;


import com.mztalk.main.domain.follow.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@Transactional
public class ProfileCustomRepositoryImpl implements ProfileCustomRepository {

    @Autowired
    EntityManager entityManager;


    @Override
    public Profile findByUserStatus(long own) {

        Profile profile = entityManager.createQuery("SELECT p FROM Profile p where p.own = :own", Profile.class)
                .setParameter("own", own)
                .getSingleResult();
        return profile;
    }
}
