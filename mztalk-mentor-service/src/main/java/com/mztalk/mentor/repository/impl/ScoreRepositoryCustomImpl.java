package com.mztalk.mentor.repository.impl;

import com.mztalk.mentor.domain.entity.Score;
import com.mztalk.mentor.repository.ScoreRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class ScoreRepositoryCustomImpl implements ScoreRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    public Double findScoreByBoardId(Long id){
        Double avgScore = entityManager.createQuery("select avg(s.count) from Board b join b.mentor m join m.scores s where b.id=:id", Double.class)
                .setParameter("id", id)
                .getSingleResult();
        return avgScore;
    }

    @Override
    public List<Score> findByUserId(Long userId) {
        List<Score> scores = entityManager.createQuery("select s from Score s join fetch s.mentee mentee join fetch s.mentor mentor where mentee.id =: userId", Score.class)
                .setParameter("userId", userId)
                .getResultList();
        return scores;
    }

    @Override
    public List<Score> findByMentorId(Long mentorId) {
        List<Score> scores = entityManager.createQuery("select s from Score s join fetch s.mentee mentee join fetch s.mentor mentor where mentor.id =: mentorId", Score.class)
                .setParameter("mentorId", mentorId)
                .getResultList();
        return scores;
    }


}
