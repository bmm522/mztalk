package com.mztalk.main.domain.dto;


import com.mztalk.main.domain.entity.status.BoardStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.mztalk.main.domain.entity.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id; //글번호
    private String nickname; //작성자
    private String title; //글제목
    private String content; //글내용
    private BoardStatus status; //글상태
    private Long own; //페이지주인
    private String privacy; //글공개범위
    private List<Reply> reply = new ArrayList<>();


//    public BoardDto(long id){
//        this.id = id;
//    }


    //레포지토리에 넣기위해
    // dto ㅡ> entity
    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .nickname(nickname)
                .title(title)
                .content(content)
                .status(BoardStatus.YES)
                .own(own)
                .privacy(privacy)
                .reply(reply)
                .build();
        return board;
    }


    // view에 뿌려줄때
    // entity ㅡ> dto
    public BoardDto(Board board){
        this.id = board.getId();
        this.nickname = board.getNickname();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.status = board.getStatus();
        this.own = board.getOwn();
        this.privacy = board.getPrivacy();
        this.reply = board.getReply();

    }

//    public Board toEntityOfId(){
//        return Board.builder()
//                .id(id)
//                .build();
//    }

}
