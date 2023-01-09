package com.mztalk.bung.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BungAddBoardGroupSelfDropRequest {
    private Long boardId;

    private Long addId;

    private Long userNo;

    private BungAddBoardGroupSelfDropRequest(Long boardId, Long addId, Long userNo) {
        this.boardId = boardId;
        this.addId = addId;
        this.userNo = userNo;
    }
}
