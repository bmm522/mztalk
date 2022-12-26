package com.mztalk.main.domain.board.repository;

import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.board.dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {

    @Query(value = "select count(*) from Board b where b.status ='YES' and b.own = :own", nativeQuery = true)
    long countByOwn(long own);

}
