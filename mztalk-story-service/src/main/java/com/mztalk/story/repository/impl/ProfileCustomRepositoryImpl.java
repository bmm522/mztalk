package com.mztalk.story.repository.impl;


import com.mztalk.story.repository.ProfileCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class ProfileCustomRepositoryImpl implements ProfileCustomRepository {

    @Autowired
    EntityManager entityManager;


//    @Override
//    public Optional<Profile> findByUserStatus(long own) {
//
//        List<Profile> profile = entityManager.createQuery("select p from Profile p where p.own =: own order by p.lastModifiedDate desc", Profile.class)
//                .setParameter("own", own)
//                .setFirstResult(1)
//                .setMaxResults(1)
//                .getResultList();
//
//        return profile.stream().findAny();
//    }
    //팔로워
//    @Override
//    public Optional<Profile> findByUserImage(Long fromUserId) {
//        List<Profile> profile = entityManager.createQuery("select p from Profile p where p.own =: own order by p.lastModifiedDate desc", Profile.class)
//                .setParameter("own", fromUserId)
//                .setFirstResult(1)
//                .setMaxResults(1)
//                .getResultList();
//
//        return profile.stream().findAny();
//    }
//    //팔로잉
//    @Override
//    public Optional<Profile> findByToUserImage(Long toUserId) {
//        List<Profile> profile = entityManager.createQuery("select p from Profile p where p.own =: own order by p.lastModifiedDate desc", Profile.class)
//                .setParameter("own", toUserId)
//                .setFirstResult(1)
//                .setMaxResults(1)
//                .getResultList();
//
//        return profile.stream().findAny();
//    }

}
