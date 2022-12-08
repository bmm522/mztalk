package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {

}
