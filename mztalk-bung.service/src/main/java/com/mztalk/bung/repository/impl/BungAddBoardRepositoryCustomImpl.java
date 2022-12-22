package com.mztalk.bung.repository.impl;

import com.mztalk.bung.domain.entity.BungAddBoard;
import com.mztalk.bung.domain.entity.QBungAddBoard;
import com.mztalk.bung.repository.BungAddBoardRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mztalk.bung.domain.BoardStatus.YES;

@Repository
public class BungAddBoardRepositoryCustomImpl implements BungAddBoardRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public BungAddBoardRepositoryCustomImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    QBungAddBoard bungAddBoard = QBungAddBoard.bungAddBoard;

    @Override
    public List<BungAddBoard> findBoardByBoardId(Long bId) {
        return entityManager.createQuery("select a from BungAddBoard a where a.bungBoard.boardId =:bId and a.boardStatus =:YES")
                .setParameter("bId", bId)
                .setParameter("status", YES)
                .getResultList();
    }
}
