package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.SearchCondition;
import com.mztalk.mentor.domain.entity.Board;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Board> searchWithCondition(SearchCondition searchCondition);
}
