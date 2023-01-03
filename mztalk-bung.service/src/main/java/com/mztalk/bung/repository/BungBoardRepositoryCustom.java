package com.mztalk.bung.repository;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.SearchKeyWord;
import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.entity.BungBoard;

import java.util.Date;
import java.util.List;


public interface BungBoardRepositoryCustom {
    int increaseCount(Long bId);

    BungBoard findBungBoardByWriterBoardId(Long boardId);

    List<BungBoard> search(SearchKeyWord searchKeyWord);

    Date findBungBoardByDeadlineDate(Long boardId);

    Long findBungBoard(Long bId);

    List<BungAddBoardDto> findBungBoardWriterAndBoardStatus(String boardWriter, String boardStatus);

//    long getRecentBoardNo();

//    int mainBoardUpdate(Long bId, BungBoardDto bungBoardDto);
}