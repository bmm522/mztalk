package com.mztalk.bung.repository;

import com.mztalk.bung.domain.SearchKeyWord;
import com.mztalk.bung.domain.entity.BungBoard;

import java.util.List;


public interface BungBoardRepositoryCustom {
    int increaseCount(Long bId);

    BungBoard findBungBoardByWriterBoardId(Long boardId);

    String findBungBoardWriter(Long boardId);

    List<BungBoard> search(SearchKeyWord searchKeyWord);

//    long getRecentBoardNo();

//    int mainBoardUpdate(Long bId, BungBoardDto bungBoardDto);
}