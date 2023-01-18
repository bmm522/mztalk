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

    private BungBoard bungBoard;

    public BungAddBoard toEntity() {
        BungAddBoard BungAddBoardEntity = BungAddBoard.builder()
                .addId(addId)
                .addContent(addContent)
                .addPhone(addPhone)
                .boardStatus(BoardStatus.NO)
                .addNickName(addNickName)
                .bungBoard(bungBoard)
                .build();
        return BungAddBoardEntity;
    }

    public BungAddBoardDto(BungAddBoard bungAddBoardEntity) {
        this.addId = bungAddBoardEntity.getAddId();
        this.addContent = bungAddBoardEntity.getAddContent();
        this.addPhone = bungAddBoardEntity.getAddPhone();
        this.boardStatus = bungAddBoardEntity.getBoardStatus();
        this.addNickName = bungAddBoardEntity.getAddNickName();
        this.bungBoard = bungAddBoardEntity.getBungBoard();
    }
}
