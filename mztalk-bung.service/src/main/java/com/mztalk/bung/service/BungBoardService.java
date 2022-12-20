package com.mztalk.bung.service;

import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.Result;
import com.mztalk.bung.domain.response.BungBoardDetailResponseDto;

import java.util.concurrent.ConcurrentHashMap;

public interface BungBoardService {

    Long mainInsertBoard(BungBoardDto bungBoardDto);

    Result mainSelectList();


    Long mainBoardUpdate(Long bId, BungBoardDto bungBoardDto);

    Long mainBoardDelete(Long bId);

    BungBoardDetailResponseDto mainBoardSelect(Long bId);

    int increaseCount(Long bId);

    ConcurrentHashMap<String, String> getRecentBoardNo();

    Long addBungBoard(BungAddBoardDto bungAddBoardDto);
}