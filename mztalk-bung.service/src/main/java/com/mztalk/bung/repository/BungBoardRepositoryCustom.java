package com.mztalk.bung.repository;

import com.mztalk.bung.domain.entity.BungBoard;


public interface BungBoardRepositoryCustom {
    int increaseCount(Long bId);

    BungBoard findBungBoardByWriterBoardId(Long boardId);

    String findBungBoardWriter(Long boardId);

//    long getRecentBoardNo();

//    int mainBoardUpdate(Long bId, BungBoardDto bungBoardDto);
}