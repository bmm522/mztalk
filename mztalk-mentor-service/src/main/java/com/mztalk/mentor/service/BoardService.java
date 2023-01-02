package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardResDto;
import com.mztalk.mentor.domain.dto.BoardTransferDto;
import com.mztalk.mentor.domain.dto.BoardReqDto;

import java.util.List;

public interface BoardService {
    Long saveBoard(BoardReqDto boardReqDto);

    BoardResDto findBoardByBoardId(Long id);

    Long delete(Long id);

    Long updateBoard(Long id, BoardResDto boardResDto);

    List<BoardResDto> searchWithCondition(SearchCondition searchCondition);

    //멘티가 본인이 신청한 멘토링 글을 보는 메소드
    List<BoardTransferDto> findBoardByUserId(Long userId);

    List<BoardResDto> findBoardByMentorId(Long mentorId);

    List<BoardResDto> latestBoard();

    List<BoardResDto> findByMentoringDateBefore();

    List<BoardResDto> findNullPaymentWithBeforeMentoringDate();

    List<BoardTransferDto> findBoardByMenteeId(Long menteeId);
}
