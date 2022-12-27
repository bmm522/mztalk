package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentor;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> searchWithCondition(SearchCondition searchCondition);

    Mentor findMentorByBoardId(Long id);

    // 멘티가 신청한 글 가져오기
    List<Board> findBoardByUserId(Long userId);

    List<Board> latestBoard();

    List<Board> findBoardByMentorId(Long mentorId);

    
}
