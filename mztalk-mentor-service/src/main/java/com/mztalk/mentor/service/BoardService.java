package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.dto.BoardDto;
import com.mztalk.mentor.domain.dto.MyBoardDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.concurrent.ConcurrentHashMap;

public interface BoardService {
    Long saveBoard(ConcurrentHashMap<String,String> boardMap);

    BoardDto findById(Long id);

    Result findAll();

    Long delete(Long mentorId);

    Long updateBoard(Long id, BoardDto boardDto);

    Result searchWithCondition(SearchCondition searchCondition);

    //멘티가 본인이 신청한 멘토링 글을 보는 메소드
    Result findBoardByUserId(Long userId);

    boolean findBoardByMentorId(Long mentorId);

    MyBoardDto getBoardByMentorId(Long mentorId);

    Result latestBoard();
}
