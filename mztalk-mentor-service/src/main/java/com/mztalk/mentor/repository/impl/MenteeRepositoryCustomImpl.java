package com.mztalk.mentor.repository.impl;

import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.repository.MenteeRepositoryCustom;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class MenteeRepositoryCustomImpl implements MenteeRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public Mentee saveClient(Mentee mentee) {
        if(mentee.getId() == null){
            entityManager.persist(mentee);
        } else{
            entityManager.merge(mentee);
        }
        return mentee;
    }



}
