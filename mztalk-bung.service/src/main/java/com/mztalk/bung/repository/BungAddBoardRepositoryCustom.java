package com.mztalk.bung.repository;

import com.mztalk.bung.domain.entity.BungAddBoard;

import java.util.List;

public interface BungAddBoardRepositoryCustom {
    List<BungAddBoard> findBoardByBoardId(Long bId);

    Long bungBoardNowGroup(Long bId);

    int findAddBoardByBoardId(Long addId);
}
