package com.mztalk.main.domain.board.service;

import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.board.dto.BoardDto;
import com.mztalk.main.common.Result;
import com.mztalk.main.domain.board.dto.BoardRequestDto;
import com.mztalk.main.domain.board.dto.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BoardService {

    //객체 간의 결합도를 낮추기 위해 interface를 만듬

    Result findByStatusOrderByBoardIdDesc(Long own, int page);

    Long save(BoardRequestDto boardRequestDto);

    Long updateBoard(Long id, BoardDto boardDto);

    Long deleteBoard(Long id);

    Result findAllByBoardStory(Long own, int page);

    int changeNickname(Map<String, String> body);

    //Long findByUserNo(String nickname);
}
