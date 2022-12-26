package com.mztalk.main.domain.board.repository;

import com.mztalk.main.domain.board.Board;

import java.util.List;

public interface BoardCustomRepository {

    List<Board> findAllByOwn(Long own);

}
