package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ScoreResDto;
import com.mztalk.mentor.domain.dto.SimpleScoreDto;
import com.mztalk.mentor.domain.dto.ScoreModifyDto;
import com.mztalk.mentor.domain.dto.ScoreReqDto;

import java.util.List;

public interface ScoreService {
    Long save(ScoreReqDto scoreReqDto);

    ScoreResDto findById(Long id);

    List<ScoreResDto> findAll();

    Long deleteScore(Long id);

    Long updateScore(Long id, ScoreModifyDto scoreModifyDto);

    boolean isExist(Long userId, Long boardId);

    List<SimpleScoreDto> findByUserId(Long userId);

    List<ScoreResDto> findByMentorId(Long mentorId);
}
