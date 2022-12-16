package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenteeRepository extends JpaRepository<Mentee,Long>,MenteeRepositoryCustom {

    @Query("select m from Mentee m where m.id=:userId")
    Mentee findMenteeByUserId(@Param("userId") Long userId);


}
