package com.mztalk.main.domain.board.repository;

import com.mztalk.main.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {
//    Iterable<Board> findByUserId(Long own);

//    @Query(value ="SELECT * FROM Board b WHERE own = :own ORDER BY b.id DESC ", nativeQuery = true)
//    List<Board> findAll(@PathVariable("own")Long own);

    long countByOwn(long own);

    Optional<Board> findByUserId(Long toUserId);




}
