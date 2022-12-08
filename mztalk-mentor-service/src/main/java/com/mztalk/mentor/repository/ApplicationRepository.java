package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
}
