package com.mztalk.mentor.repository.impl;

import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.repository.MenteeRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class MenteeRepositoryCustomImpl implements MenteeRepositoryCustom {
    private final EntityManager entityManager;


    @Override
    public Mentee findMenteeByUserId(Long userId) {
        return entityManager.createQuery("select m from Mentee m where m.id =:userId", Mentee.class)
                .setParameter("userId",userId)
                .getSingleResult();
    }
}
