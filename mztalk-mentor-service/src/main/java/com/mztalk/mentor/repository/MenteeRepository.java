package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.Entity.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenteeRepository extends JpaRepository<Mentee,Long> {

}
