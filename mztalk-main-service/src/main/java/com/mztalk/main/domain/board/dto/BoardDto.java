package com.mztalk.main.domain.board.dto;


import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.status.BoardStatus;
import com.mztalk.main.status.PrivacyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id; //글번호
   // private Board nickname; //작성자
    private String title; //글제목
    private String content; //글내용
    private BoardStatus status; //글상태
    private Long own; //페이지주인
    private PrivacyStatus privacy; //글공개범위

    private List<Reply> replyList = new ArrayList<>();

    private LocalDateTime lastModifiedDate;

    private String nickname;

    //레포지토리에 넣기위해
    // 계층간의 데이터 전송을 위한
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
                .reply(replyList)
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
        this.replyList = board.getReplyList();
        this.lastModifiedDate = board.getLastModifiedDate();

    }





}
