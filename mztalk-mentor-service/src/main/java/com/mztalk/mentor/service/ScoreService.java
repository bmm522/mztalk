package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ScoreDto;
import com.mztalk.mentor.domain.dto.ScoreMenteeDto;
import com.mztalk.mentor.domain.dto.ScoreModifyDto;
import com.mztalk.mentor.domain.dto.ScoreReqDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface ScoreService {
    Long save(ScoreReqDto scoreDto);

    ScoreDto findById(Long id);

    List<ScoreDto> findAll();

    Long deleteScore(Long id);

    Long updateScore(Long id, ScoreModifyDto scoreDto);

    List<ScoreDto> findScoresByNickname(String nickname);

    boolean isExist(Long userId, Long boardId);

    List<ScoreMenteeDto> findByUserId(Long userId);

    List<ScoreDto> findByMentorId(Long mentorId);
}
