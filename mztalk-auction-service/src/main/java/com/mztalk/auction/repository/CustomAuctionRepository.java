package com.mztalk.auction.repository;

import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;

import java.util.List;


public interface CustomAuctionRepository {
    int boardUpdate(Long bId, BoardEditDto boardEditDto);

    int deleteBoard(Long bId, String writer);

    int updatePrice(BoardPriceDto boardPriceDto);

    int updateCount(Long bId);



    int updateComment(Long cId, CommentUpdateRequestDto commentUpdateRequestDto);

    int deleteComment(Long cId);

    List<Board> selectBoardList();

    List<Board> searchBoard(String keyword);

    List<Comment> selectCommentList(Long bId);

    void updateIsClose(long boardId);
}
