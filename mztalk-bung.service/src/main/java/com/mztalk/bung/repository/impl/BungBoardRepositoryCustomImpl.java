package com.mztalk.bung.repository.impl;

import com.mztalk.bung.domain.SearchKeyWord;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.entity.QBungBoard;
import com.mztalk.bung.repository.BungBoardRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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

    @Override
    @Transactional
    public BungBoard findBungBoardByWriterBoardId(Long boardId) {
        return (BungBoard) entityManager.createQuery("select b.boardId from BungBoard b where b.boardId = :boardId")
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

    @Override
    @Transactional
    public String findBungBoardWriter(Long boardId) {
        return (String) entityManager.createQuery("select b.boardWriter from BungBoard b where b.boardId = :boardId")
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

    // 카테고리, 키워드별 검색 기능 로직
    @Override
    @Transactional
    public List<BungBoard> search(SearchKeyWord searchKeyWord) {
        return queryFactory.selectFrom(bungBoard)
                .where(eqCategory(searchKeyWord.getCategory()),
                    (containsBoardTitle(searchKeyWord.getBoardTitle())),
                    (containsBoardContent(searchKeyWord.getBoardContent())),
                    (containsBoardWriter(searchKeyWord.getBoardWriter()))
                ).fetch();
    }

    @Override
    public Date findBungBoardByDeadlineDate(Long boardId) {
        return (java.sql.Date) entityManager.createQuery("select b.deadlineDate from BungBoard b where b.boardId = :boardId")
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

    private BooleanExpression eqCategory(String category) {
        return category != null ? bungBoard.category.contains(category) : null;
    }

    private BooleanExpression containsBoardTitle(String boardTitle) {
        return boardTitle != null ? bungBoard.boardTitle.contains(boardTitle) : null;
    }

    private BooleanExpression containsBoardContent(String boardContent) {
        return boardContent != null ? bungBoard.boardContent.contains(boardContent) : null;
    }

    private BooleanExpression containsBoardWriter(String boardWriter) {
        return boardWriter != null ? bungBoard.boardWriter.contains(boardWriter) : null;
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