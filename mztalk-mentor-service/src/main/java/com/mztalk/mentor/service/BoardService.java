package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.dto.BoardMenteeDto;
import com.mztalk.mentor.domain.dto.BoardReqDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface BoardService {
    Long saveBoard(BoardReqDto boardDto);

    BoardDto findBoardByBoardId(Long id);

    Long delete(Long id);

    Long updateBoard(Long id, BoardDto boardDto);

    List<BoardDto> searchWithCondition(SearchCondition searchCondition);

    //멘티가 본인이 신청한 멘토링 글을 보는 메소드
    List<BoardMenteeDto> findBoardByUserId(Long userId);

    List<BoardDto> findBoardByMentorId(Long mentorId);

    List<BoardDto> latestBoard();

    List<BoardDto> findByMentoringDateBefore();

    List<BoardDto> findNullPaymentWithBeforeMentoringDate();

    List<BoardMenteeDto> findBoardByMenteeId(Long menteeId);
}
