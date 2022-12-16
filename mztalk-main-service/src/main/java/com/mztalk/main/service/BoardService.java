package com.mztalk.main.service;

import com.mztalk.main.domain.dto.BoardDto;
import com.mztalk.main.domain.entity.Result;

public interface BoardService {

    //객체 간의 결합도를 낮추기 위해 interface를 만듬

    Result findAllByOwn(Long own);

    Long save(BoardDto boardDto);

    Long updateBoard(Long id, BoardDto boardDto);

    Long deleteBoard(Long id, BoardDto boardDto);
}
