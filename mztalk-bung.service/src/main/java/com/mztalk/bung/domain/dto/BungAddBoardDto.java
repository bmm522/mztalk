package com.mztalk.bung.domain.dto;

import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.entity.BungAddBoard;
import com.mztalk.bung.domain.entity.BungBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BungAddBoardDto {

    private Long addId;

    private String addContent;

    private String addPhone;

    private BoardStatus boardStatus;

    private String addNickName;

    private BungBoard boardId;

    public BungAddBoard toEntity() {
        BungAddBoard BungAddBoardEntity = BungAddBoard.builder()
                .addId(addId)
                .addContent(addContent)
                .addPhone(addPhone)
                .boardStatus(BoardStatus.YES)
                .addNickName(addNickName)
                .boardId(boardId)
                .build();
        return BungAddBoardEntity;
    }

    public BungAddBoardDto(BungAddBoard BungAddBoardEntity) {
        this.addId = BungAddBoardEntity.getAddId();
        this.addContent = BungAddBoardEntity.getAddContent();
        this.addPhone = BungAddBoardEntity.getAddPhone();
        this.boardStatus = BungAddBoardEntity.getBoardStatus();
        this.addNickName = BungAddBoardEntity.getAddNickName();
        this.boardId = BungAddBoardEntity.getBoardId();
    }
}
