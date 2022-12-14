package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends JpaRepository<Score,Long>,ScoreRepositoryCustom {


}
