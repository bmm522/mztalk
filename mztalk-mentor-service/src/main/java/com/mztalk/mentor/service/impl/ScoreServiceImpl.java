package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.*;
import com.mztalk.mentor.domain.entity.*;
import com.mztalk.mentor.exception.BoardNotFoundException;
import com.mztalk.mentor.exception.ScoreNotFoundException;
import com.mztalk.mentor.repository.BoardRepository;
import com.mztalk.mentor.repository.MenteeRepository;
import com.mztalk.mentor.repository.MentorRepository;
import com.mztalk.mentor.repository.ScoreRepository;
import com.mztalk.mentor.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final MenteeRepository menteeRepository;
    private final MentorRepository mentorRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long save(ScoreReqDto scoreReqDto) {

        Mentee mentee = menteeRepository.findMenteeByUserId(scoreReqDto.getUserId());
        Mentor mentor = boardRepository.findMentorByBoardId(scoreReqDto.getBoardId());
        Board board = boardRepository.findById(scoreReqDto.getBoardId()).orElseThrow(()-> new BoardNotFoundException("해당 번호의 글이 존재하지 않습니다."));

        Score score = scoreReqDto.toEntity();
        score.addMentee(mentee);
        score.addMentor(mentor);
        score.addBoard(board);

        Score savedScore = scoreRepository.save(score);
        return savedScore.getId();
    }

    @Override
    public ScoreResDto findById(Long id) {
        Score score = scoreRepository.findById(id).orElseThrow(() -> new ScoreNotFoundException("해당 번호의 리뷰가 존재하지 않습니다."));
        ScoreResDto scoreResDto = new ScoreResDto(score,new MenteeTransferDto(score.getMentee()),new MentorTransferDto(score.getMentor()));
        return scoreResDto;
    }

    // 멘티가 해당 글에 대해 리뷰를 작성했는지 확인한다.
    @Override
    public boolean isExist(Long userId, Long boardId) {
        Score score = scoreRepository.isExist(userId, boardId);
        boolean isExist = score == null ? false : true;
        return isExist;
    }

    @Override
    public List<SimpleScoreDto> findByUserId(Long userId) {
        List<Score> scores = scoreRepository.findByUserId(userId);
        List<SimpleScoreDto> collect = scores.stream().map(SimpleScoreDto::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<ScoreResDto> findByMentorId(Long mentorId) {
        List<Score> scores = scoreRepository.findByMentorId(mentorId);
        List<ScoreResDto> collect = scores.stream().map(ScoreResDto::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<ScoreResDto> findAll() {
        List<Score> scoreList = scoreRepository.findAll();
        List<ScoreResDto> collect = scoreList.stream()
                .map(s->new ScoreResDto(s,new MenteeTransferDto(s.getMentee()),new MentorTransferDto(s.getMentor())))
                .collect(Collectors.toList());
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
    public Long updateScore(Long id, ScoreModifyDto scoreModifyDto) {
        Score score = scoreRepository.findById(id).orElseThrow(() -> new ScoreNotFoundException("해당하는 평점이 존재하지 않습니다."));
        score.updateScore(scoreModifyDto);
        return score.getId();
    }

}
