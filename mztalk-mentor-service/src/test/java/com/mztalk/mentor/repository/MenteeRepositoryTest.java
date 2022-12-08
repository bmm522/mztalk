package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.Mentee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MenteeRepositoryTest {

    @Autowired
    private MenteeRepository menteeRepository;

    @Test
    public void saveTest() throws Exception {
        //given
        Mentee mentee1 = new Mentee();
        Mentee mentee2 = new Mentee();

        //when
        Mentee savedMentee1 = menteeRepository.save(mentee1);
        Mentee savedMentee2 = menteeRepository.save(mentee2);

        Mentee findMentee1 = menteeRepository.findById(mentee1.getId()).get();
        Mentee findMentee2 = menteeRepository.findById(mentee2.getId()).get();
        //then
        assertThat(mentee1).isEqualTo(savedMentee1);
        assertThat(mentee2).isEqualTo(savedMentee2);

        assertThat(findMentee1).isEqualTo(mentee1);
        assertThat(findMentee2).isEqualTo(mentee2);

        assertThat(mentee1.getId()).isEqualTo(savedMentee1.getId());
        assertThat(mentee2.getId()).isEqualTo(savedMentee2.getId());
    }

    @Test
    public void findTest() throws Exception {
        //given
        Mentee mentee1 = new Mentee();
        Mentee mentee2 = new Mentee();

        mentee1.setNickname("nickname1");
        mentee2.setNickname("nickname2");

        menteeRepository.save(mentee1);
        menteeRepository.save(mentee2);
        //when
        Mentee findMentee1 = menteeRepository.findById(mentee1.getId()).get();
        Mentee findMentee2 = menteeRepository.findById(mentee2.getId()).get();

        //then
        assertThat(findMentee1.getNickname()).isEqualTo("nickname1");
        assertThat(findMentee2.getNickname()).isEqualTo("nickname2");
    }

    @Test
    public void findAllTest() throws Exception {
        //given
        Mentee mentee1 = new Mentee();
        Mentee mentee2 = new Mentee();
        Mentee mentee3 = new Mentee();

        menteeRepository.save(mentee1);
        menteeRepository.save(mentee2);
        menteeRepository.save(mentee3);

        //when
        List<Mentee> menteeList = menteeRepository.findAll();

        //then
        assertThat(menteeList.size()).isEqualTo(3);
        assertThat(menteeList.get(1)).isEqualTo(mentee2);

    }

}