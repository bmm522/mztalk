package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.OpenApiAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<OpenApiAccessToken,Long> {
}
