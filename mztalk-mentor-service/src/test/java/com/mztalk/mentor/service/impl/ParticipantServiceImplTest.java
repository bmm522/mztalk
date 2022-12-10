package com.mztalk.mentor.service.impl;


import com.mztalk.mentor.repository.ParticipantRepository;
import com.mztalk.mentor.service.ParticipantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ParticipantServiceImplTest {

    @Autowired
    ParticipantService participantService;
    @Autowired
    ParticipantRepository participantRepository;

    @Test
    public void save() throws Exception {
        //given



        //when

        //then

    }
}