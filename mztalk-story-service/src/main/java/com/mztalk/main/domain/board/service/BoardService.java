package com.mztalk.main.domain.board.service;

import com.mztalk.main.common.Result;
import com.mztalk.main.domain.board.dto.BoardNicknameModifyDto;
import com.mztalk.main.domain.board.dto.BoardRequestDto;
import com.mztalk.main.domain.board.dto.BoardResponseDto;

import java.util.Map;

public interface BoardService {

    //객체 간의 결합도를 낮추기 위해 interface를 만듬

    Result findByStatusOrderByBoardIdDesc(Long own, int page);

    Long save(BoardRequestDto boardRequestDto);

    Long updateBoard(Long id, BoardResponseDto boardResponseDto);

    Long deleteBoard(Long id);

    Result findAllByBoardStory(Long own, int page);

    Long modifyNickname(BoardNicknameModifyDto boardNicknameModifyDto);

    //Long findByUserNo(String nickname);
}
