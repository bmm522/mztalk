package com.mztalk.auction.service;

import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.entity.Board;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface AuctionService {


    Long insertBoard(BoardDto board);

    int updateBoard(Long bId, BoardDto boardDto);

    List<Board> selectBoardList();

    int deleteBoard(Long bId);

    Board selectBoard(Long bId);

    int updatePrice(Long bId, BoardDto boardDto);

    int updateCount(Long bId);
}
