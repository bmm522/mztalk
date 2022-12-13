package com.mztalk.main.repository;

import com.mztalk.main.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {
//    Iterable<Board> findByUserId(Long own);

    @Query(value ="SELECT * FROM Board b WHERE own = :own ORDER BY b.id DESC ", nativeQuery = true)
    List<Board> findAll(@PathVariable("own")Long own);
}
