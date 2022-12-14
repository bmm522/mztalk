package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.ScoreDto;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.domain.entity.Result;
import com.mztalk.mentor.domain.entity.Score;
import com.mztalk.mentor.exception.ScoreNotFoundException;
import com.mztalk.mentor.repository.MenteeRepository;
import com.mztalk.mentor.repository.MentorRepository;
import com.mztalk.mentor.repository.ScoreRepository;
import com.mztalk.mentor.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final MenteeRepository menteeRepository;
    private final MentorRepository mentorRepository;

    @Override
    @Transactional
    public Long save(ConcurrentHashMap<String,String> scoreDto) {
        Long userId = Long.parseLong(scoreDto.get("userId"));
        Long mentorId = Long.parseLong(scoreDto.get("mentorId"));

        Mentee mentee = menteeRepository.findMenteeByUserId(userId);
        Mentor mentor = mentorRepository.findMentorByUserId(mentorId);

        Score score = Score.createScore(scoreDto, mentee, mentor);
        return scoreRepository.save(score).getId();
    }

    @Override
    public ScoreDto findById(Long id) {
        Score score = scoreRepository.findById(id).orElseThrow(() -> new ScoreNotFoundException("해당하는 평점이 존재하지 않습니다."));
        ScoreDto scoreDto = new ScoreDto(score);
        return scoreDto;
    }

    @Override
    public Result findAll() {
        List<Score> scoreList = scoreRepository.findAll();
        List<ScoreDto> collect = scoreList.stream().map(ScoreDto::new).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    @Transactional
    public Long deleteScore(Long id) {
        Score score = scoreRepository.findById(id).orElseThrow(() -> new ScoreNotFoundException("해당하는 평점이 존재하지 않습니다."));
        scoreRepository.delete(score);
        return score.getId();
    }

    @Override
    @Transactional
    public Long updateScore(Long id, ScoreDto scoreDto) {
        Score score = scoreRepository.findById(id).orElseThrow(() -> new ScoreNotFoundException("해당하는 평점이 존재하지 않습니다."));
        score.updateScore(scoreDto);
        return score.getId();
    }
}
