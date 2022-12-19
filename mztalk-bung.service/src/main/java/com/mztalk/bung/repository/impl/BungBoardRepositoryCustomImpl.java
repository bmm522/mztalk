package com.mztalk.bung.repository.impl;

import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.QBungBoard;
import com.mztalk.bung.repository.BungBoardRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class BungBoardRepositoryCustomImpl implements BungBoardRepositoryCustom {
    @Autowired
    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public BungBoardRepositoryCustomImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    QBungBoard bungBoard = QBungBoard.bungBoard;


    @Override
    @Transactional
    public int increaseCount(Long bId) {
        return entityManager.createQuery("update BungBoard b set b.boardCount = b.boardCount + 1 where b.boardId = :bId")
                .setParameter("bId", bId)
                .executeUpdate();
    }

//    @Override
//    public long getRecentBoardNo() {
//        return (long) entityManager.createQuery("SELECT b.boardId FROM BungBoard b order by b.boardId DESC limit 1")
//                .getFirstResult();
//    }

//    @Override
//    @Transactional
//    public int mainBoardUpdate(Long bId, BungBoardDto bungBoardDto) {
//        return entityManager.createQuery("update BungBoard b set b.boardTitle = :boardTitle, b.boardContent = :boardContent, b.deadlineDate = :deadlineDate, b.fullGroup = :fullGroup, b.category = :category where b.boardId = :bId and b.boardWriter = :boardWriter")
//                .setParameter("boardTitle", bungBoardDto.getBoardTitle())
//                .setParameter("boardContent", bungBoardDto.getBoardContent())
//                .setParameter("deadlineDate", bungBoardDto.getDeadlineDate())
//                .setParameter("fullGroup", bungBoardDto.getFullGroup())
//                .setParameter("category", bungBoardDto.getCategory())
//                .executeUpdate();
//    }

}
