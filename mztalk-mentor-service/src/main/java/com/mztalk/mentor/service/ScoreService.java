package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ScoreDto;
import com.mztalk.mentor.domain.dto.ScoreMenteeDto;
import com.mztalk.mentor.domain.entity.Result;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface ScoreService {
    Long save(ConcurrentHashMap<String,String> scoreMap);

    ScoreDto findById(Long id);

    List<ScoreDto> findAll();

    Long deleteScore(Long id);

    Long updateScore(Long id, ScoreDto scoreDto);

    List<ScoreDto> findScoresByNickname(String nickname);

    boolean isExist(Long userId, Long boardId);

    List<ScoreMenteeDto> findByUserId(Long userId);

    List<ScoreDto> findByMentorId(Long mentorId);
}
