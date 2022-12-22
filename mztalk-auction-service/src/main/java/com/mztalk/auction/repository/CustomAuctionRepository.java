package com.mztalk.auction.repository;

import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.dto.CommentDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public interface CustomAuctionRepository {
    int boardUpdate(Long bId, BoardDto boardDto);

    int deleteBoard(Long bId, String writer);

    int updatePrice(Long bId, BoardDto boardDto);

    int updateCount(Long bId);



    int updateComment(Long cId, CommentDto commentDto);

    int deleteComment(Long cId, CommentDto commentDto);

    List<Board> selectBoardList();

}
