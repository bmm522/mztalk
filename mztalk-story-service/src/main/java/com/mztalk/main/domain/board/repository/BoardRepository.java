package com.mztalk.main.domain.board.repository;

import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.board.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {

    @Query(value = "select count(*) from Board b where b.status ='YES' and b.own = :own", nativeQuery = true)
    long countByOwn(long own);


    @Modifying
    @Query("UPDATE Board b SET b.writer = :nickname WHERE b.own = :userNo")
    int updateNickname(long userNo, String nickname);


    Board findByOwn(Long own);
}
