package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardTransferDto {

    private Long id;
    private String title;
    private String nickname;
    private Status status;

    public BoardTransferDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.nickname = board.getNickname();
        this.status = board.getStatus();
    }

}
