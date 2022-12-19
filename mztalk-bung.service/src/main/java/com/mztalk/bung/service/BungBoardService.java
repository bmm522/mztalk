package com.mztalk.bung.service;

import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.Result;

public interface BungBoardService {

    Long mainInsertBoard(BungBoardDto bungBoardDto);

    Result mainSelectList();


    Long mainBoardUpdate(Long bId, BungBoardDto bungBoardDto);

    Long mainBoardDelete(Long bId);

    BungBoardDto mainBoardSelect(Long bId);

    int increaseCount(Long bId);
}
