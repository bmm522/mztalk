package com.mztalk.bung.domain.dto;


import com.mztalk.bung.domain.BoardStatus;
import com.mztalk.bung.domain.entity.BungBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BungBoardDto {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private String boardContent;
    private String deadlineDate;
    private Long fullGroup;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private Long boardCount;
    private BoardStatus boardStatus;
    private String category;

    public BungBoard toEntity() {
        BungBoard BungBoardEntity = BungBoard.builder()
                .boardId(boardId)
                .boardWriter(boardWriter)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .deadlineDate(Date.valueOf(deadlineDate))
                .fullGroup(fullGroup)
                .createDate(createDate)
                .modifyDate(modifyDate)
                .boardCount(boardCount)
                .boardStatus(BoardStatus.YES)
                .category(category)
                .build();
        return BungBoardEntity;
    }

    public BungBoardDto(BungBoard BungBoardEntity) {
        this.boardId = BungBoardEntity.getBoardId();
        this.boardWriter = BungBoardEntity.getBoardWriter();
        this.boardTitle = BungBoardEntity.getBoardTitle();
        this.boardContent = BungBoardEntity.getBoardContent();
        this.deadlineDate = String.valueOf(BungBoardEntity.getDeadlineDate());
        this.fullGroup = BungBoardEntity.getFullGroup();
        this.createDate = BungBoardEntity.getCreateDate();
        this.modifyDate = BungBoardEntity.getModifyDate();
        this.boardCount = BungBoardEntity.getBoardCount();
        this.boardStatus = BungBoardEntity.getBoardStatus();
        this.category = BungBoardEntity.getCategory();
    }
}
