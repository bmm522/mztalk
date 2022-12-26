package com.mztalk.main.domain.board.repository;

import com.mztalk.main.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {

    @Query(value = "select count(*) from Board b where b.status ='YES' and b.own = :own", nativeQuery = true)
    long countByOwn(long own);

}
