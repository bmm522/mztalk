package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepositoryCustom {
    List<Board>  searchWithCondition(SearchCondition searchCondition);

    Mentor findMentorByBoardId(Long id);

    // 멘티가 신청한 글 가져오기
    List<Board> findBoardByUserId(Long userId, LocalDateTime now);

    Page<Board> latestBoard(Pageable pageable);

    List<Board> findBoardByMentorId(Long mentorId);

    // 메인페이지 출력 메소드, 결제가 안되고 멘토링 전 글만 출력된다.
    Page<Board> findNullPaymentWithBeforeMentoringDate(LocalDateTime now, Pageable pageable);

    
}
