package com.mztalk.auction.service;

import com.mztalk.auction.domain.dto.BoardRequestDto;
import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.dto.CommentDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface AuctionService {


    Long insertBoard(BoardRequestDto boardRequestDto);

    int updateBoard(Long bId, BoardDto boardDto);

    List<Board> selectBoardList();

    int deleteBoard(Long bId);

    Board selectBoard(Long bId);

    int updatePrice(Long bId, BoardDto boardDto);

    int updateCount(Long bId);

    ConcurrentHashMap<String, String> getRecentBoardNo();

    Comment insertComment(CommentDto commentDto, Long bId);

    int updateComment(Long cId, CommentDto commentDto);

    int deleteComment(Long cId, CommentDto commentDto);
}
