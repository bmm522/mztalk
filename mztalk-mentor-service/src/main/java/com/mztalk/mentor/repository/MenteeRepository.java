package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenteeRepository extends JpaRepository<Mentee,Long> {

}
