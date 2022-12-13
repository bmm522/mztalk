package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MentorRepository extends JpaRepository<Mentor,Long> {

    @Query("select m from Mentor m where m.mentorNickname =:mentorNickname")
    Mentor findByMentorNickname(@Param("mentorNickname") String mentorNickname);
}
