package com.mztalk.mentor.repository.impl;

import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.repository.ApplicationRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class ApplicationRepositoryCustomImpl implements ApplicationRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Application>  fetchMenteeApplication() {
        List<Application> resultList = entityManager.createQuery("select a from Application a join fetch a.mentee m", Application.class)
                .getResultList();
        return resultList;
    }
}
