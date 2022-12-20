package com.mztalk.auction.repository.impl;

import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.dto.CommentDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import com.mztalk.auction.repository.CustomAuctionRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CustomAuctionRepositoryImpl implements CustomAuctionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public int boardUpdate(Long bId, BoardDto boardDto) {
        return entityManager.createQuery("update Board b set b.title= :title, b.content= :content where b.bId = :bId and b.writer = :writer")
                .setParameter("title", boardDto.getTitle())
                .setParameter("content", boardDto.getContent())
                .setParameter("bId", bId)
                .setParameter("writer", boardDto.getWriter())
                .executeUpdate();
    }

    @Transactional
    @Override
    public int deleteBoard(Long bId) {
        return entityManager.createQuery("update Board b set b.status = 'N' where b.bId = :bId")
                .setParameter("bId", bId)
                .executeUpdate();
    }

    @Transactional
    @Override
    public int updatePrice(Long bId, BoardDto boardDto) {
        System.out.println(boardDto.getContent());
        return entityManager.createQuery("update Board b set b.currentPrice = :currentPrice where b.bId = :bId")
                .setParameter("currentPrice", boardDto.getCurrentPrice())
                .setParameter("bId", bId)
                .executeUpdate();
    }

    @Transactional
    @Override
    public int updateCount(Long bId) {
        return entityManager.createQuery("update Board b set b.count = b.count + 1 where b.bId = :bId")
                .setParameter("bId", bId)
                .executeUpdate();
    }

    @Override
    public long getRecentBoardNo() {
        return entityManager.createQuery("SELECT b.bId from Board b order by b.bId DESC limit 1")
                .getFirstResult();
    }

    @Transactional
    @Override
    public int updateComment(Long cId, CommentDto commentDto) {
        return entityManager.createQuery("update Comment c set c.content = :content where c.cId = :cId and c.nickname = :nickname")
                .setParameter("content", commentDto.getContent())
                .setParameter("cId", cId)
                .setParameter("nickname", commentDto.getNickname())
                .executeUpdate();
    }

    @Transactional
    @Override
    public int deleteComment(Long cId, CommentDto commentDto) {
        return entityManager.createQuery("update Comment c set c.status = 'N' where c.cId = :cId and c.nickname = :nickname")
                .setParameter("cId", cId)
                .setParameter("nickname", commentDto.getNickname())
                .executeUpdate();
    }


}
