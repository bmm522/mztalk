package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Result;

import java.util.concurrent.ConcurrentHashMap;

public interface BoardService {
    Long saveBoard(ConcurrentHashMap<String,String> boardMap);

    BoardDto findById(Long id);

    Result findAll();

    Long delete(Long id);

    Long updateBoard(Long id, BoardDto boardDto);

    Result searchWithCondition(SearchCondition searchCondition);

    Result findBoardByUserId(Long userId);
}
