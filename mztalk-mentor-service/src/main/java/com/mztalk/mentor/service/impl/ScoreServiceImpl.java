package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.ScoreDto;
import com.mztalk.mentor.domain.dto.ScoreMenteeDto;
import com.mztalk.mentor.domain.entity.*;
import com.mztalk.mentor.exception.BoardNotFoundException;
import com.mztalk.mentor.exception.ScoreNotFoundException;
import com.mztalk.mentor.repository.BoardRepository;
import com.mztalk.mentor.repository.MenteeRepository;
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
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long save(ConcurrentHashMap<String,String> scoreMap) {
        Long userId = Long.parseLong(scoreMap.get("userId"));
        Long boardId = Long.parseLong(scoreMap.get("boardId"));

        Mentee mentee = menteeRepository.findMenteeByUserId(userId);
        Mentor mentor = boardRepository.findMentorByBoardId(boardId);
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new BoardNotFoundException("해당 번호의 글이 존재하지 않습니다."));

        Score score = Score.createScore(scoreMap, mentee, mentor, board);
        return scoreRepository.save(score).getId();
    }

    @Override
    public ScoreDto findById(Long id) {
        Score score = scoreRepository.findById(id).orElseThrow(() -> new ScoreNotFoundException("해당 번호의 리뷰가 존재하지 않습니다."));
        ScoreDto scoreDto = new ScoreDto(score);
        return scoreDto;
    }

    @Override
    public List<ScoreDto> findScoresByNickname(String nickname) {
        List<Score> scores = scoreRepository.findByNickname(nickname);
        List<ScoreDto> collect = scores.stream().map(ScoreDto::new).collect(Collectors.toList());
        return collect;
    }

    // 멘티가 해당 글에 대해 리뷰를 작성했는지 확인한다.
    @Override
    public boolean isExist(Long userId, Long boardId) {
        Score score = scoreRepository.isExist(userId, boardId);
        boolean isExist = score == null ? false : true;
        return isExist;
    }

    @Override
    public List<ScoreMenteeDto> findByUserId(Long userId) {
        List<Score> scores = scoreRepository.findByUserId(userId);
        List<ScoreMenteeDto> collect = scores.stream().map(ScoreMenteeDto::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<ScoreDto> findByMentorId(Long mentorId) {
        List<Score> scores = scoreRepository.findByMentorId(mentorId);
        List<ScoreDto> collect = scores.stream().map(ScoreDto::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<ScoreDto> findAll() {
        List<Score> scoreList = scoreRepository.findAll();
        List<ScoreDto> collect = scoreList.stream().map(ScoreDto::new).collect(Collectors.toList());
        return collect;
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
