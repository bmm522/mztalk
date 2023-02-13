package com.mztalk.story.repository;

import com.mztalk.story.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepository {

    Page<Board> findByStatusOrderByBoardIdDesc(Long own, Pageable pageable);

    Page<Board> findAllByBoardStory(Long own, Pageable pageable);
}
