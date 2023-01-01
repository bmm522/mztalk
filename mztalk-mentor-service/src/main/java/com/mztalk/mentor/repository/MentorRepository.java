package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MentorRepository extends JpaRepository<Mentor,Long>,MentorRepositoryCustom {

    @Query("select m from Mentor m where m.userId =:userId")
    Mentor findMentorByUserId(@Param("userId") Long userId);



}
