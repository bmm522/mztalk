package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Mentor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MentorRepositoryTest {

    @Autowired
    private MentorRepository mentorRepository;

    @Test
    public void saveTest() throws Exception {
        //given
        Mentor mentor1 = Mentor.builder().build();
        Mentor mentor2 = Mentor.builder().build();

        //when
        Mentor savedMentor1 = mentorRepository.save(mentor1);
        Mentor savedMentor2 = mentorRepository.save(mentor2);

        //then
        assertThat(mentor1).isEqualTo(savedMentor1);
        assertThat(mentor2).isEqualTo(savedMentor2);
    }

    @Test
    public void findTest() throws Exception {
        //given
        Mentor mentor1 = Mentor.builder().build();
        Mentor mentor2 = Mentor.builder().build();

        mentorRepository.save(mentor1);
        mentorRepository.save(mentor2);

        //when
        Mentor findMentor1 = mentorRepository.findById(mentor1.getId()).get();
        Mentor findMentor2 = mentorRepository.findById(mentor2.getId()).get();

        //then
        assertThat(mentor1).isEqualTo(findMentor1);
        assertThat(mentor2).isEqualTo(findMentor2);
    }

    @Test
    public void findAllTest() throws Exception {
        //given
        Mentor mentor1 = Mentor.builder().build();
        Mentor mentor2 = Mentor.builder().build();
        mentorRepository.save(mentor1);
        mentorRepository.save(mentor2);

        //when
        List<Mentor> mentorList = mentorRepository.findAll();

        //then
        assertThat(mentorList.size()).isEqualTo(2);
        assertThat(mentorList.get(0)).isEqualTo(mentor1);
        assertThat(mentorList.get(1)).isEqualTo(mentor2);
    }


}