package com.mztalk.bung.repository;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.dto.BungAddBoardDto;
import com.mztalk.bung.domain.dto.BungBoardDto;
import com.mztalk.bung.domain.entity.BungAddBoard;
import com.mztalk.bung.domain.entity.BungBoard;
import com.mztalk.bung.domain.response.BungListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BungBoardRepository extends JpaRepository<BungBoard, Long>, BungBoardRepositoryCustom {

    BungBoard findFirstByOrderByBoardIdDesc();

    BungBoard findBungBoardByBoardId(Long boardId);

    Page<BungBoard> findByBoardStatus(BoardStatus yes, Pageable pageable);

    List<BungBoardDto> findByBoardWriter(String boardWriter);

//    List<BungAddBoardDto> findBungBoardWriterAndBoardStatus(String boardWriter, BoardStatus boardStatus);
}