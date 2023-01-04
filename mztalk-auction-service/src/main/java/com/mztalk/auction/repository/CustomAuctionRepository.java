package com.mztalk.auction.repository;

import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CustomAuctionRepository {
    int boardUpdate(Long bId, BoardEditDto boardEditDto);

    int deleteBoard(Long bId, String writer);

    void updatePrice(BoardPriceDto boardPriceDto);

    int updateCount(Long bId, String writer);



    int updateComment(Long cId, CommentUpdateRequestDto commentUpdateRequestDto);

    int deleteComment(Long cId);

    List<Board> selectBoardList();

    Page<Board> searchBoard(String keyword, Pageable pageable);

    List<Comment> selectCommentList(Long bId);

    void updateIsClose(long boardId);

    int closeBoard(Long boardId);
}
