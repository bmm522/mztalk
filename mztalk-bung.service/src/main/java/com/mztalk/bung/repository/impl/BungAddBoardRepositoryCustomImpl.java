package com.mztalk.bung.repository.impl;

import com.mztalk.bung.domain.entity.BungAddBoard;
import com.mztalk.bung.domain.entity.QBungAddBoard;
import com.mztalk.bung.repository.BungAddBoardRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.mztalk.bung.domain.BoardStatus.NO;
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
    public Optional<String> findAddBoardByWriter(Long boardId, String addWriter) {
        Optional<String> writer = Optional.empty();
        try {
            return Optional.ofNullable(entityManager.createQuery("select a.addNickName from BungAddBoard a where a.bungBoard.boardId = :boardId and a.addNickName = :addWriter", String.class)
                    .setParameter("boardId", boardId)
                    .setParameter("addWriter", addWriter)
                    .getSingleResult());
        } catch (NoResultException e){
            return writer;
        }
    }

    @Transactional
    @Modifying
    @Override
    public int deleteByBoardId(Long bId) {
        return entityManager.createQuery("delete from BungAddBoard a where a.bungBoard.boardId = :bId")
                .setParameter("bId", bId)
                .executeUpdate();
    }

    @Transactional
    @Modifying
    @Override
    public Long bungAddBoardGroupDrop(Long bId, Long aId) {
        return (long) entityManager.createQuery("delete from BungAddBoard a where a.bungBoard.boardId = :bId and a.addId = :aId and a.boardStatus = :YES")
                .setParameter("bId", bId)
                .setParameter("aId", aId)
                .setParameter("YES", YES)
                .executeUpdate();
    }

    @Override
    public List<BungAddBoard> getAcceptList(String nickname) {
        List<Long> boardIdList= entityManager.createQuery("SELECT b.boardId FROM BungBoard b WHERE b.boardWriter = :nickname", Long.class)
                .setParameter("nickname", nickname)
                .getResultList();
        return entityManager.createQuery("SELECT b FROM BungAddBoard b WHERE b.bungBoard.boardId IN :boardId AND b.boardStatus = :NO", BungAddBoard.class)
                .setParameter("boardId", boardIdList)
                .setParameter("NO", NO)
                .getResultList();
    }

    @Transactional
    @Modifying
    @Override
    public Long addBungRefuse(Long addId, long boardId) {
        return (long) entityManager.createQuery("delete from BungAddBoard a where a.bungBoard.boardId = :boardId and a.addId = :addId")
                .setParameter("addId", addId)
                .setParameter("boardId", boardId)
                .executeUpdate();
    }

//    @Override
//    public String findAddBoardByWriter(Long boardId) {
//        return (String) entityManager.createQuery("select a.addNickName from BungAddBoard a where a.bungBoard.boardId = :boardId")
//                .setParameter("boardId", boardId)
//                .getSingleResult();
//    }
}
