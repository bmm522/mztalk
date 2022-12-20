package com.mztalk.auction.repository;

import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.dto.CommentDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public interface CustomAuctionRepository {
    int boardUpdate(Long bId, BoardDto boardDto);

    int deleteBoard(Long bId);

    int updatePrice(Long bId, BoardDto boardDto);

    int updateCount(Long bId);

    long getRecentBoardNo();

    int updateComment(Long cId, CommentDto commentDto);

    int deleteComment(Long cId, CommentDto commentDto);
}
