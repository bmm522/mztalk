package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.entity.Result;

public interface BoardService {
    Long saveBoard(BoardDto boardDto);

    BoardDto findById(Long id);

    Result findAll();

    Long delete(Long id);

    Long updateBoard(Long id, BoardDto boardDto);
}
