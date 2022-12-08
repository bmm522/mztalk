package com.mztalk.mentor.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MentorRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MentorRepository mentorRepository;

    @Test
    public void saveTest() throws Exception {
        //given

        //when

        //then

    }

}