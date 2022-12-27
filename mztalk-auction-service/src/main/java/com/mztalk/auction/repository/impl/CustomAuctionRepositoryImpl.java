package com.mztalk.auction.repository.impl;

import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.dto.BoardEditDto;
import com.mztalk.auction.domain.dto.CommentDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import com.mztalk.auction.repository.CustomAuctionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomAuctionRepositoryImpl implements CustomAuctionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //전체 게시글 조회, 검색
    @Override
    public List<Board> selectBoardList() {
        return entityManager.createQuery("select b from Board b where b.status = 'Y'")
                .getResultList();
    }

    //게시글 검색
    @Override
    public List<Board> searchBoard(String keyword) {
        return entityManager.createQuery("select b from Board b where b.status = 'Y' and b.title like concat('%', :keyword, '%') or b.content like concat('%', :keyword, '%')")
                .setParameter("keyword", keyword)
                .getResultList();
    }


    //게시글 수정
    @Transactional
    @Override
    public int boardUpdate(Long bId, BoardEditDto boardEditDto) {
        return entityManager.createQuery("update Board b set b.title= :title, b.content= :content where b.boardId = :bId")
                .setParameter("title", boardEditDto.getTitle())
                .setParameter("content", boardEditDto.getContent())
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
    public int updatePrice(Long bId, BoardDto boardDto) {
        System.out.println(boardDto.getContent());
        return entityManager.createQuery("update Board b set b.currentPrice = :currentPrice where b.boardId = :bId")
                .setParameter("currentPrice", boardDto.getCurrentPrice())
                .setParameter("bId", bId)
                .executeUpdate();
    }

    //조회수 증가
    @Transactional
    @Override
    public int updateCount(Long bId) {
        return entityManager.createQuery("update Board b set b.count = b.count + 1 where b.boardId = :bId")
                .setParameter("bId", bId)
                .executeUpdate();
    }


    //댓글 수정
    @Transactional
    @Override
    public int updateComment(Long cId, CommentDto commentDto) {
        return entityManager.createQuery("update Comment c set c.content = :content where c.cId = :cId and c.writer = :writer")
                .setParameter("content", commentDto.getContent())
                .setParameter("cId", cId)
                .setParameter("writer", commentDto.getWriter())
                .executeUpdate();
    }

    //댓글 삭제
    @Transactional
    @Override
    public int deleteComment(Long cId, CommentDto commentDto) {
        return entityManager.createQuery("update Comment c set c.status = 'N' where c.cId = :cId and c.writer = :writer")
                .setParameter("cId", cId)
                .setParameter("writer", commentDto.getWriter())
                .executeUpdate();
    }

    //댓글 전체 조회
    @Override
    public List<CommentDto> selectCommentList() {
        return entityManager.createQuery("select c from Comment c where status = 'Y'")
                .getResultList();
    }




}
