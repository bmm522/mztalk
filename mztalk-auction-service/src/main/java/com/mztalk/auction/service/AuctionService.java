package com.mztalk.auction.service;

import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface AuctionService {


    Long insertBoard(BoardRequestDto boardRequestDto);

    int updateBoard(Long bId, BoardEditDto boardEditDtoDto);

    Result<?> selectBoardList() throws ParseException;

    int deleteBoard(Long bId, String writer);

    BoardDetailResponseDto selectBoard(Long bId);

    int updatePrice(Long bId, BoardDto boardDto);

    int updateCount(Long bId);

    ConcurrentHashMap<String, String> getRecentBoardNo();

    Comment insertComment(CommentRequestDto commentRequestDto);

    int updateComment(Long cId, CommentDto commentDto);

    int deleteComment(Long cId, CommentDto commentDto);

    Result<?> searchBoard(String keyword) throws ParseException;

    Result<?> selectCommentList();
}
