package com.mztalk.main.domain.entity;


import com.mztalk.main.domain.entity.status.FriendStatus;
import com.mztalk.main.domain.entity.status.ReplyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@NoArgsConstructor
@Getter
@Builder
@Table(name="Reply")
@Entity
public class Reply extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //댓글번호

    @Lob
    @Column(nullable = false)
    private String replyContent; //댓글내용

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    private String replyNickname; //작성자

    @Enumerated(EnumType.STRING)
    private ReplyStatus status; // 댓글 status

    @Builder
    public Reply(Long id, String replyContent, Board board, String replyNickname, ReplyStatus status ){
        this.id = id;
        this.replyContent = replyContent;
        this.board = board;
        this.replyNickname = replyNickname;
        this.status = status;
    }

    //댓글삭제(status = N)
    public void changeReplyStatus(){
        this.status = ReplyStatus.NO;
    }

}
