package com.mztalk.bung.domain.response;

import com.mztalk.bung.domain.entity.BungBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BungBoardDetailResponseDto {

    private String boardId;

    private long boardWriterId;
    private String title;
    private String writer;
    private String createDate;
    private List<ConcurrentHashMap<String, String>> imageInfo;
    private String content;
    private String nowGroup;
    private String fullGroup;
    private String category;
    private String deadlineDate;
    private String address;

    public BungBoardDetailResponseDto(BungBoard bungBoard, List<ConcurrentHashMap<String, String>> mapList) {
        this.boardId = String.valueOf(bungBoard.getBoardId());
        this.boardWriterId = bungBoard.getBoardWriterId();
        this.title = bungBoard.getBoardTitle();
        this.writer = bungBoard.getBoardWriter();
        this.createDate = String.valueOf(bungBoard.getCreateDate());
        this.imageInfo = mapList;
        this.content = bungBoard.getBoardContent();
//        this.nowGroup = String.valueOf(bungBoard.getNowGroup());
        this.fullGroup = String.valueOf(bungBoard.getFullGroup());
        this.category = bungBoard.getCategory();
        this.deadlineDate = String.valueOf(bungBoard.getDeadlineDate());
        this.address = bungBoard.getAddress();
    }
}
