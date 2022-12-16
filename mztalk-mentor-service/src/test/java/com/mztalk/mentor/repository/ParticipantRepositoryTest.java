package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class ParticipantRepositoryTest {
    @Autowired
    private ParticipantRepository participantRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void saveTest() throws Exception {
        //given
        Participant participant1 = Participant.builder().name("menteeA").build();
        Participant participant2 = Participant.builder().name("menteeB").build();

        //when
        Participant savedParticipant1 = participantRepository.save(participant1);
        Participant savedParticipant2 = participantRepository.save(participant2);

        //then
        assertThat(savedParticipant1).isEqualTo(participant1);
        assertThat(savedParticipant2).isEqualTo(participant2);
        assertThat(savedParticipant1.getName()).isEqualTo("menteeA");
        assertThat(savedParticipant2.getName()).isEqualTo("menteeB");
    }

    @Test
    public void findTest() throws Exception {
        //given
        Board math = Board.builder().title("수학").salary(10000).build();
        Board english = Board.builder().title("영어").salary(20000).build();

        em.persist(math);
        em.persist(english);
        em.flush();
        em.clear();

        Participant participant1 = Participant.builder().board(math).name("menteeA").build();
        Participant participant2 = Participant.builder().board(english).name("menteeB").build();

        participantRepository.save(participant1);
        participantRepository.save(participant2);

        //when
        Participant findParticipant1 = participantRepository.findById(participant1.getId()).get();
        Participant findParticipant2 = participantRepository.findById(participant2.getId()).get();
        List<Participant> participantList = participantRepository.findAll();

        //then
        assertThat(participantList.size()).isEqualTo(2);
        assertThat(findParticipant1).isEqualTo(participant1);
        assertThat(findParticipant2).isEqualTo(participant2);
        assertThat(findParticipant1.getBoard().getTitle()).isEqualTo("수학");
        assertThat(findParticipant2.getBoard().getTitle()).isEqualTo("영어");
        assertThat(findParticipant1.getBoard().getSalary()).isEqualTo(10000);
        assertThat(findParticipant2.getBoard().getSalary()).isEqualTo(20000);
        assertThat(findParticipant1.getName()).isEqualTo("menteeA");
        assertThat(findParticipant2.getName()).isEqualTo("menteeB");
    }



}