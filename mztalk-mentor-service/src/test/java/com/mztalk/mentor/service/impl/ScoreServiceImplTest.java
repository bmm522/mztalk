//package com.mztalk.mentor.service.impl;
//
//import com.mztalk.mentor.domain.dto.MentorDto;
//import com.mztalk.mentor.domain.dto.ScoreDto;
//import com.mztalk.mentor.domain.entity.Mentor;
//import com.mztalk.mentor.domain.entity.Score;
//import com.mztalk.mentor.repository.ScoreRepository;
//import com.mztalk.mentor.service.ScoreService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//class ScoreServiceImplTest {
//    @Autowired
//    private ScoreService scoreService;
//    @Autowired
//    private ScoreRepository scoreRepository;
//
//    @Test
//    public void test() throws Exception {
//        //given
//        ScoreDto scoreDto1 = new ScoreDto();
//        ScoreDto scoreDto2 = new ScoreDto();
//
//        scoreDto1.setCount(5.0D);
//        scoreDto1.setContent("수학");
//
//        scoreDto2.setCount(4.0D);
//        scoreDto2.setContent("영어");
//        //when
//        Long savedId1 = scoreService.save(scoreDto1);
//        Long savedId2 = scoreService.save(scoreDto2);
//
//        ScoreDto savedDto1 = scoreService.findById(savedId1);
//        ScoreDto savedDto2 = scoreService.findById(savedId2);
//
//        List<Score> scoreList = scoreRepository.findAll();
//        //then
//        assertThat(savedDto1.getCount()).isEqualTo(savedDto1.getCount());
//        assertThat(savedDto2.getCount()).isEqualTo(savedDto2.getCount());
//        assertThat(savedDto1.getContent()).isEqualTo(savedDto1.getContent());
//        assertThat(savedDto2.getContent()).isEqualTo(savedDto2.getContent());
//        assertThat(scoreList.size()).isEqualTo(2);
//    }
//
//    @Test
//    public void saveMentorTest() throws Exception {
//        //given
//        MentorDto mentorDto1 = new MentorDto();
//        MentorDto mentorDto2 = new MentorDto();
//
//        Mentor mentor1 = mentorDto1.toEntity();
//        Mentor mentor2 = mentorDto2.toEntity();
//
//        ScoreDto scoreDto1 = new ScoreDto();
//        ScoreDto scoreDto2 = new ScoreDto();
//
//        scoreDto1.setMentor(mentor1);
//        scoreDto2.setMentor(mentor2);
//
//        scoreService.save(scoreDto1);
//        scoreService.save(scoreDto2);
//        //when
//        Mentor savedMentor1 = scoreDto1.getMentor();
//        Mentor savedMentor2 = scoreDto2.getMentor();
//        //then
//        assertThat(savedMentor1).isEqualTo(mentor1);
//        assertThat(savedMentor2).isEqualTo(mentor2);
//    }
//
//    @Test
//    public void delete() throws Exception {
//        //given
//        ScoreDto scoreDto1 = new ScoreDto();
//        ScoreDto scoreDto2 = new ScoreDto();
//
//        Long savedId1 = scoreService.save(scoreDto1);
//        Long savedId2 = scoreService.save(scoreDto2);
//        //when
//        scoreService.deleteScore(savedId1);
//        scoreService.deleteScore(savedId2);
//
//        List<Score> scoreList = scoreRepository.findAll();
//        //then
//        assertThat(scoreList.size()).isEqualTo(0);
//    }
//
//}