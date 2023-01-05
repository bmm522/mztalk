package com.mztalk.bung.service;

import com.mztalk.bung.domain.SearchKeyWord;
import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.response.BungAddRequestDto;
import com.mztalk.bung.domain.entity.Result;
import com.mztalk.bung.domain.response.BungBoardDetailResponseDto;
import com.mztalk.bung.domain.response.ResponseDto;

import java.util.concurrent.ConcurrentHashMap;

public interface BungBoardService {

    Long mainInsertBoard(BungBoardDto bungBoardDto);

    Result<?> mainSelectList(int page);

    Long mainBoardUpdate(Long bId, BungBoardDto bungBoardDto);

    Long mainBoardDelete(Long bId);

    BungBoardDetailResponseDto mainBoardSelect(Long bId);

    int increaseCount(Long bId);

    ConcurrentHashMap<String, String> getRecentBoardNo();

    BungAddRequestDto addBungBoard(ConcurrentHashMap<String, String> bungAddBoardMap);

    Result addBungBoardsList(String boardWriter);

    Long addBungBoardUpdate(Long addId, BungAddBoardDto bungAddBoardDto);

    BungAddRequestDto addBungBoardAccept(Long addId);

    BungAddBoardDto bungAddBoardSelect(Long aId);

    Result bungRequestList(Long bId);

    Long addBungBoardDelete(Long addId);

    Long bungBoardNowGroup(Long bId);

    Result<?> bungBoardSearch(SearchKeyWord searchKeyWord);

    Long bungAddBoardGroupDrop(Long bId, Long aId);

    Long findBungBoard(Long bId);

    Result<?> search(String[] categories, String type, String searchText, int page);

    Result<?> getAcceptList(String nickname);

    Result<?> mainSelectListOfMain(int page);

    BungAddRequestDto addBungRefuse(Long addId);
}