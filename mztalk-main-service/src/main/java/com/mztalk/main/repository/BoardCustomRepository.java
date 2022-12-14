package com.mztalk.main.repository;

import com.mztalk.main.domain.entity.Board;

import java.util.List;

public interface BoardCustomRepository {

    List<Board> findAllByOwn(Long own);
}
