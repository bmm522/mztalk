package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.Mentee;
import com.mztalk.mentor.domain.Entity.Mentor;
import com.mztalk.mentor.domain.Entity.Score;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class ScoreRepositoryTest {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private MenteeRepository menteeRepository;

    @Test
    public void saveTest() throws Exception {
        //given
        Score score1 = new Score();
        Score score2 = new Score();

        //when
        Score savedScore1 = scoreRepository.save(score1);
        Score savedScore2 = scoreRepository.save(score2);
        //then
        assertThat(score1).isEqualTo(savedScore1);
        assertThat(score2).isEqualTo(savedScore2);
    }

    @Test
    public void findTest() throws Exception {
        //given
        Mentee mentee1 = new Mentee();
        mentee1.setNickname("menteeA");

        Mentee mentee2 = new Mentee();
        mentee2.setNickname("menteeB");

        em.persist(mentee1);
        em.persist(mentee2);
        em.flush();
        em.clear();

        Score score1 = new Score();
        Score score2 = new Score();

        score1.setCount(5.0D);
        score2.setCount(4.0D);

        score1.setMentee(mentee1);
        score2.setMentee(mentee2);

        scoreRepository.save(score1);
        scoreRepository.save(score2);

        //when
        Score findScore1 = scoreRepository.findById(score1.getId()).get();
        Score findScore2 = scoreRepository.findById(score2.getId()).get();


        List<Score> scoreList = scoreRepository.findAll();

        //then
        assertThat(scoreList.size()).isEqualTo(2);
        assertThat(scoreList.get(0).getMentee()).isEqualTo(mentee1);
        assertThat(scoreList.get(1).getMentee()).isEqualTo(mentee2);
        assertThat(findScore1.getCount()).isEqualTo(5.0D);
        assertThat(findScore2.getCount()).isEqualTo(4.0D);
    }

}