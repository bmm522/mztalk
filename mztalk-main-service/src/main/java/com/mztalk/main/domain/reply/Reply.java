package com.mztalk.main.domain.reply;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mztalk.main.common.BaseTimeEntity;
import com.mztalk.main.domain.board.Board;
import com.mztalk.main.status.ReplyStatus;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="Reply")
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //댓글번호

    @Lob
//    @Column(nullable = false)
    private String replyContent; //댓글내용

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE) // 연관된 user가 삭제되면 같이 삭제됨
    @JoinColumn(name="BoardId")
    private Board board;

    @OnDelete(action = OnDeleteAction.CASCADE) // 연관된 작성자가 삭제되면 같이 삭제됨
    private String replyNickname; //작성자

    @Enumerated(EnumType.STRING)
    private ReplyStatus status; // 댓글 status

    private Long replyUserNo;

    @CreatedDate
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;


    //댓글삭제(status = N)
    public void changeReplyStatus(){
        this.status = ReplyStatus.NO;
    }

}
