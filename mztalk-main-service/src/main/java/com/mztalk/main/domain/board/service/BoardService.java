package com.mztalk.main.domain.board.service;

import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.common.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    //객체 간의 결합도를 낮추기 위해 interface를 만듬

    Result findByStatusOrderByBoardIdDesc(Long own, int page);

    Board save(BoardDto boardDto);

    Long updateBoard(Long id, BoardDto boardDto);

    Long deleteBoard(Long id);

    Result findAllByBoardStory(Long own, int page);

    //Long findByUserNo(String nickname);
}
