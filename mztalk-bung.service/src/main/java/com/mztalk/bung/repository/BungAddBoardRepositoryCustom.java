package com.mztalk.bung.repository;

import com.mztalk.bung.domain.entity.BungAddBoard;

import java.util.List;
import java.util.Optional;

public interface BungAddBoardRepositoryCustom {
    List<BungAddBoard> findBoardByBoardId(Long bId);

    Long bungBoardNowGroup(Long bId);

    int findAddBoardByBoardId(Long addId);

    Optional<String> findAddBoardByWriter(Long boardId, String addWriter);

    int deleteByBoardId(Long bId);

    Long bungAddBoardGroupDrop(Long bId, Long aId);

    List<BungAddBoard> getAcceptList(String nickname);

    Long addBungRefuse(Long addId, long boardId);

//    String findAddBoardByWriter(Long boardId, addWriter);
}
