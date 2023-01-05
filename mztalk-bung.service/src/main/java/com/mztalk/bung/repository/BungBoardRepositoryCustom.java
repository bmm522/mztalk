package com.mztalk.bung.repository;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.SearchKeyWord;
import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.response.BungBoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;


public interface BungBoardRepositoryCustom {
    int increaseCount(Long bId, String boardWriter);

    BungBoard findBungBoardByWriterBoardId(Long boardId);

    List<BungBoard> search(SearchKeyWord searchKeyWord);

    Date findBungBoardByDeadlineDate(Long boardId);

    Long findBungBoard(Long bId);

   // List<BungBoard> getSearchList(String[] categories, String type, String searchText, java.awt.print.Pageable pageable);

    List<BungAddBoardDto> findBungBoardWriterAndBoardStatus(String boardWriter, String boardStatus);

    Page<BungBoard> getSearchList(String[] categories, String type, String searchText, Pageable pageable);

//    long getRecentBoardNo();

//    int mainBoardUpdate(Long bId, BungBoardDto bungBoardDto);
}