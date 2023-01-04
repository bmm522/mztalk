package com.mztalk.auction.repository;

import com.mztalk.auction.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, CustomAuctionRepository {


    Board findByBoardId(Long bId);


    Board findFirstByOrderByBoardIdDesc();

    List<Board> findByIsCloseAndStatus(String isClose, String status);

    void findByWriter(String writer);

    Page<Board> findByIsCloseAndStatusOrderByBoardIdDesc(String isClose, String stauts, Pageable pageable);

    Page<Board> findByStatusOrderByBoardIdDesc(String status, Pageable pageable);
}
