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
                .setParameter("YES", YES)
                .getResultList();
    }

    @Override
    public Long bungBoardNowGroup(Long bId) {
        return (Long) entityManager.createQuery("select count(*) from BungAddBoard a where a.boardStatus =:YES and a.bungBoard.boardId = :bId")
                .setParameter("bId", bId)
                .setParameter("YES", YES)
                .getSingleResult();
    }

    @Override
    public int findAddBoardByBoardId(Long addId) {
        return  entityManager.createQuery("select a.bungBoard.boardId as boardId from BungAddBoard a where a.addId =:addId")
                .setParameter("addId", addId)
                .getFirstResult();
    }

    @Override
    public String findAddBoardByWriter(Long boardId, String addWriter) {
        return (String) entityManager.createQuery("select a.addNickName from BungAddBoard a where a.bungBoard.boardId = :boardId and a.addNickName = :addWriter")
                .setParameter("boardId", boardId)
                .setParameter("addWriter", addWriter)
                .getSingleResult();
    }

//    @Override
//    public String findAddBoardByWriter(Long boardId) {
//        return (String) entityManager.createQuery("select a.addNickName from BungAddBoard a where a.bungBoard.boardId = :boardId")
//                .setParameter("boardId", boardId)
//                .getSingleResult();
//    }
}
