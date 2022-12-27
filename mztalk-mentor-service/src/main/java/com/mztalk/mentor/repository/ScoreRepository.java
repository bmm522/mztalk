package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends JpaRepository<Score,Long>,ScoreRepositoryCustom {

    @Query("select s from Score s join fetch s.mentee m join fetch s.board b where m.id =:userId and b.id =:boardId")
    Score isExist(@Param("userId") Long userId, @Param("boardId") Long boardId);
}
