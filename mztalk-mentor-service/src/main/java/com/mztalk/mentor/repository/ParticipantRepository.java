package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {

    @Query("select p from Participant p join fetch p.board b join fetch p.mentee m where b.mentor.id =:mentorId")
    List<Participant> findParticipantsByMentorId(@Param("mentorId")Long mentorId);

    @Query("select p from Participant p join fetch p.board b join fetch b.payment pay where pay.id =:paymentId")
    Participant findByPaymentId(@Param("paymentId")Long paymentId);

    @Query("select p from Participant p join fetch p.board b where b.id=:boardId")
    Participant existParticipant(@Param("boardId") Long boardId);
}
