package com.mztalk.login.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ReportCustomRepositoryImpl implements ReportCustomRepository{

    @Autowired
    EntityManager entityManager;

    @Override
    public long postReport(long boardId, long id, String serviceName) {
            return entityManager.createQuery("UPDATE Report r SET r.reportStatus = 'N' WHERE r.boardId = :boardId AND r.user.id = :id AND r.serviceName = :serviceName")
                .setParameter("boardId", String.valueOf(boardId))
                .setParameter("id", id)
                .setParameter("serviceName", serviceName)
                .executeUpdate();
    }
}
