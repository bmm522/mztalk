package com.mztalk.auction.repository.impl;

import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import com.mztalk.auction.repository.CustomAuctionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class CustomAuctionRepositoryImpl implements CustomAuctionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //전체 게시글 조회, 검색
    @Override
    public List<Board> selectBoardList() {
        return entityManager.createQuery("select b from Board b where b.status = 'Y' order by b.boardId desc")
                .getResultList();
    }

    //게시글 검색
    @Override
    public Page<Board> searchBoard(String keyword, Pageable pageable) {
        List boards = entityManager.createQuery("select b from Board b where b.status = 'Y' and b.title like concat('%', :keyword, '%') or b.content like concat('%', :keyword, '%') order by b.boardId desc")
                .setParameter("keyword", keyword)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        int total = entityManager.createQuery("select count(b) from Board b where b.status = 'Y' and b.title like concat('%', :keyword, '%') or b.content like concat('%', :keyword, '%')")
                .setParameter("keyword", keyword)
                .getFirstResult();
        return new PageImpl<>(boards, pageable, total);
    }

    //게시글 수정
    @Transactional
    @Override
    public int boardUpdate(Long bId, BoardEditDto boardEditDto) {
        return entityManager.createQuery("update Board b set b.title= :title, b.content= :content, b.bookTitle = :bookTitle, b.isbn = :isbn where b.boardId = :bId")
                .setParameter("title", boardEditDto.getTitle())
                .setParameter("content", boardEditDto.getContent())
                .setParameter("bookTitle", boardEditDto.getBookTitle())
                .setParameter("isbn", boardEditDto.getIsbn())
                .setParameter("bId", bId)
                .executeUpdate();
    }

    //게시글 삭제
    @Transactional
    @Override
    public int deleteBoard(Long bId, String writer) {
        return entityManager.createQuery("update Board b set b.status = 'N' where b.boardId = :bId and b.writer = :writer")
                .setParameter("bId", bId)
                .setParameter("writer", writer)
                .executeUpdate();
    }

    //입찰가
    @Transactional
    @Override
    public void updatePrice(BoardPriceDto boardPriceDto) {
        entityManager.createQuery("update Board b set b.currentPrice = :currentPrice, b.buyerNickname = :nickName where b.boardId = :boardId")
                .setParameter("currentPrice", boardPriceDto.getCurrentPrice())
                .setParameter("nickName", boardPriceDto.getBuyer())
                .setParameter("boardId", boardPriceDto.getBoardId())
                .executeUpdate();
    }

    //조회수 증가
    @Transactional
    @Override
    public int updateCount(Long bId, String writer) {
        return entityManager.createQuery("update Board b set b.count = b.count + 1 where b.boardId = :bId and b.writer != :writer")
                .setParameter("bId", bId)
                .setParameter("writer", writer)
                .executeUpdate();
    }


    //댓글 수정
    @Transactional
    @Override
    public int updateComment(Long cId, CommentUpdateRequestDto commentUpdateRequestDto) {
        return entityManager.createQuery("update Comment c set c.content = :content where c.commentId = :cId")
                .setParameter("content", commentUpdateRequestDto.getContent())
                .setParameter("cId", cId)
                .executeUpdate();
    }

    //댓글 삭제
    @Transactional
    @Override
    public int deleteComment(Long cId) {
        return entityManager.createQuery("update Comment c set c.status = 'N' where c.commentId = :cId")
                .setParameter("cId", cId)
                .executeUpdate();
    }

    //댓글 전체 조회
    @Override
    public List<Comment> selectCommentList(Long bId) {
        return entityManager.createQuery("select c from Comment c where c.status = 'Y' and c.board.boardId = :bId", Comment.class)
                .setParameter("bId", bId)
                .getResultList();
    }

    //입찰 마감
    @Transactional
    @Override
    public void updateIsClose(long boardId) {
        System.out.println("repository: " + boardId);
        entityManager.createQuery("update Board b set b.isClose = 'Y' where b.boardId = :boardId")
                .setParameter("boardId", boardId)
                .executeUpdate();
    }

    //지금 마감시키기
    @Transactional
    @Override
    public int closeBoard(Long boardId) {
        String nowDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return entityManager.createQuery("update Board b set b.timeLimit = :timeLimit, b.isClose = 'Y' where b.boardId = :boardId")
                .setParameter("boardId", boardId)
                .setParameter("timeLimit", nowDateTime)
                .executeUpdate();
    }


}
