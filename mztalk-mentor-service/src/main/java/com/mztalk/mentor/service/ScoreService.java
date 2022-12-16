package com.mztalk.mentor.service;

import com.mztalk.mentor.domain.dto.ScoreDto;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.domain.entity.Score;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface ScoreService {
    Long save(ConcurrentHashMap<String,String> scoreDto);

    Double findById(Long id);

    Result findAll();

    Long deleteScore(Long id);

    Long updateScore(Long id, ScoreDto scoreDto);

    Result findScoresByNickname(String nickname);

}
