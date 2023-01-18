package com.mztalk.bung.repository.impl;

import com.mztalk.bung.domain.SearchKeyWord;
import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.entity.QBungBoard;
import com.mztalk.bung.repository.BungBoardRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Arrays;
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
    public int increaseCount(Long bId, String boardWriter) {
        System.out.println("boardWriter : " + boardWriter);
        return entityManager.createQuery("update BungBoard b set b.boardCount = b.boardCount + 1 where b.boardId = :bId")
                .setParameter("bId", bId)
//                .setParameter("boardWriter", boardWriter)
                .executeUpdate();
    }

    @Override
    @Transactional
    public BungBoard findBungBoardByWriterBoardId(Long boardId) {
        return (BungBoard) entityManager.createQuery("select b.boardId from BungBoard b where b.boardId = :boardId")
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

//    @Override
//    @Transactional
//    public String findBungBoardWriter(Long boardId) {
//        return (String) entityManager.createQuery("select b.boardWriter from BungBoard b where b.boardId = :boardId", String.class)
//                .setParameter("boardId", boardId)
//                .getSingleResult();
//    }

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

    @Override
    public Long findBungBoard(Long bId) {
        return entityManager.createQuery("select count(*) from BungBoard b where b.boardId = :bId", Long.class)
                .setParameter("bId", bId)
                .getSingleResult();
    }



//    @Override
//    public List<BungBoard> getSearchList(String[] categories, String type, String searchText) {
//
//        return entityManager.createQuery
//                        ("SELECT b FROM BungBoard b WHERE b.category IN :categories AND CASE :type WHEN 'boardTitle' THEN b.boardTitle LIKE CONCAT('%', :searchText, '%') WHEN 'boardContent' THEN b.boardContent LIKE CONCAT('%', :searchText, '%') ELSE b.boardWriter = :searchText END", BungBoard.class)
//                .setParameter("categories", Arrays.asList(categories))
//                .setParameter("type", type)
//                .setParameter("searchText", searchText)
//                .getResultList();
//    }

    @Override
    public Page<BungBoard> getSearchList(String[] categories, String type, String searchText, Pageable pageable) {
        long totalSearchResults = entityManager.createQuery
                        ("SELECT COUNT(b) FROM BungBoard b WHERE b.category IN :categories AND CASE :type WHEN 'boardTitle' THEN b.boardTitle WHEN 'boardContent' THEN b.boardContent ELSE b.boardWriter END LIKE CONCAT('%', :searchText, '%')", Long.class)
                .setParameter("categories", Arrays.asList(categories))
                .setParameter("type", type)
                .setParameter("searchText", searchText)
                .getSingleResult();

        List<BungBoard> searchResults = entityManager.createQuery
                        ("SELECT b FROM BungBoard b WHERE b.category IN :categories AND CASE :type WHEN 'boardTitle' THEN b.boardTitle WHEN 'boardContent' THEN b.boardContent ELSE b.boardWriter END LIKE CONCAT('%', :searchText, '%')", BungBoard.class)
                .setParameter("categories", Arrays.asList(categories))
                .setParameter("type", type)
                .setParameter("searchText", searchText)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(searchResults, pageable, totalSearchResults);
    }

    @Override
    public List<BungAddBoardDto> findBungBoardWriterAndBoardStatus(String boardWriter, String boardStatus) {
       return null;
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