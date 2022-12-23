package com.mztalk.main.domain.profile.repository;


import com.mztalk.main.domain.profile.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProfileCustomRepositoryImpl implements ProfileCustomRepository {

    @Autowired
    EntityManager entityManager;


    @Override
    public Optional<Profile> findByUserStatus(long own) {

        List<Profile> profile = entityManager.createQuery("select p from Profile p where p.own =: own order by p.lastModifiedDate desc", Profile.class)
                .setParameter("own", own)
                .setFirstResult(1)
                .setMaxResults(1)
                .getResultList();

        return profile.stream().findAny();
    }

}
