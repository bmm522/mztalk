package com.mztalk.auction.service;

import com.mztalk.auction.domain.Result;
import com.mztalk.auction.domain.dto.*;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Comment;

import java.text.ParseException;
import java.util.concurrent.ConcurrentHashMap;

public interface AuctionService {


    Long insertBoard(BoardRequestDto boardRequestDto);

    int updateBoard(Long bId, BoardEditDto boardEditDtoDto);

    Result<?> selectBoardList(int page) throws ParseException;

    int deleteBoard(Long bId, String writer);

    BoardDetailResponseDto selectBoard(Long bId);

    BoardPriceDto updatePrice(BoardPriceDto boardPriceDto);

    int updateCount(Long bId, String writer);

    ConcurrentHashMap<String, String> getRecentBoardNo();

    CommentResponseDto insertComment(CommentRequestDto commentRequestDto);

    CommentResponseDto updateComment(Long cId, CommentUpdateRequestDto commentUpdateRequestDto);

    int deleteComment(Long cId);

    Result<?> searchBoard(String keyword, int page) throws ParseException;

    Result<?> selectCommentList(Long bId);

    CommentResponseDto selectComment(Long cId);

    Result<?> selectCloseBoardList(int page) throws ParseException;

    int closeBoard(BoardCloseDto boardCloseDto);

    void postChatRoom(BoardDto boardDto);

    Result<?> selectBoardListOfFront(int page) throws ParseException;
}
