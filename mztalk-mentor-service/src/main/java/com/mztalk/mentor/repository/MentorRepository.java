package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor,Long> {

}
