package com.mztalk.bung.repository;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.entity.BungAddBoard;
import com.mztalk.bung.domain.entity.BungBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BungBoardRepository extends JpaRepository<BungBoard, Long>, BungBoardRepositoryCustom {

    BungBoard findFirstByOrderByBoardIdDesc();

    BungBoard findBungBoardByBoardId(Long boardId);

    List<BungBoard> findByBoardStatus(BoardStatus yes);
}