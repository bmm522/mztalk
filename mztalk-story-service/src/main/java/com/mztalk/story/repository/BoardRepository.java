package com.mztalk.story.repository;

import com.mztalk.story.domain.board.Board;
import com.mztalk.story.repository.BoardCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {

    @Query(value = "select count(*) from Board b where b.status ='YES' and b.own = :own", nativeQuery = true)
    long countByOwn(long own);


    @Modifying
    @Query("UPDATE Board b SET b.writer = :nickname WHERE b.own = :userNo")
    int updateNickname(long userNo, String nickname);


    Board findByOwn(Long own);
}
