package com.mztalk.mentor.repository.impl;

import com.mztalk.mentor.repository.ScoreRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class ScoreRepositoryCustomImpl implements ScoreRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    public Double findScoreByBoardId(Long id){
        Double avgScore = entityManager.createQuery("select avg(s.count) from Board b join b.mentor m join m.scores s where b.id=:id", Double.class)
                .setParameter("id", id)
                .getSingleResult();
        return avgScore;
    }
}
