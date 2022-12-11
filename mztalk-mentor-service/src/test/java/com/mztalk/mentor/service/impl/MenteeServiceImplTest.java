package com.mztalk.mentor.service.impl;


import com.mztalk.mentor.domain.dto.MenteeDto;
import com.mztalk.mentor.domain.dto.MentorDto;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Mentor;
import com.mztalk.mentor.repository.MenteeRepository;
import com.mztalk.mentor.service.MenteeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MenteeServiceImplTest {
    @Autowired
    MenteeService menteeService;
    @Autowired
    MenteeRepository menteeRepository;


    @Test
    public void test() throws Exception {
        //given
        MentorDto mentorDto1 = new MentorDto();
        MentorDto mentorDto2 = new MentorDto();

        Mentor mentor1 = mentorDto1.toEntity();
        Mentor mentor2 = mentorDto2.toEntity();

        ArrayList<Mentor> mentors = new ArrayList<>();
        mentors.add(mentor1);
        mentors.add(mentor2);

        //when
        MenteeDto menteeDto = new MenteeDto();
        menteeDto.setNickname("Jake");
        menteeDto.setMentors(mentors);
        Long savedId = menteeService.saveClient(menteeDto);

        Mentee mentee = menteeRepository.findById(savedId).get();
        //then
        assertThat(mentors.size()).isEqualTo(2);
        assertThat(mentee.getNickname()).isEqualTo(menteeDto.getNickname());
        assertThat(mentee.getMentors().get(0)).isEqualTo(mentor1);
        assertThat(mentee.getMentors().get(1)).isEqualTo(mentor2);
    }


}