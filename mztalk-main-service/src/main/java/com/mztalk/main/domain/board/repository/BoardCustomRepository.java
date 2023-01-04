package com.mztalk.main.domain.board.repository;

import com.mztalk.main.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardCustomRepository {

    Page<Board> findAllByOwn(Long own, Pageable pageable);

    Page<Board> findAllByBoardStory(Long own, Pageable pageable);
}
