package com.mztalk.bung.repository;

import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.BungBoard;


public interface BungBoardRepositoryCustom {
    int increaseCount(Long bId);

//    int mainBoardUpdate(Long bId, BungBoardDto bungBoardDto);
}
