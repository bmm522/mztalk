package com.mztalk.auction.service;

import com.mztalk.auction.domain.dto.BoardDto;

import java.util.concurrent.ConcurrentHashMap;

public interface AuctionService {


    Long insertBoard(BoardDto board);

    void updateBoard(Long bId, BoardDto boardDto);
}
