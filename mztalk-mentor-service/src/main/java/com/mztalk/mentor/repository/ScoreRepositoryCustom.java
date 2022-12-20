package com.mztalk.mentor.repository;


import com.mztalk.mentor.domain.entity.Score;

import java.util.List;

public interface ScoreRepositoryCustom {

    Double findScoreByBoardId(Long id);

    List<Score> findByNickname(String nickname);

    List<Score> findByUserId(Long userId);

    List<Score> findByMentorId(Long mentorId);
}
