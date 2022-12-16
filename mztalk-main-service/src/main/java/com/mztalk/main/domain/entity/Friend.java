package com.mztalk.main.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mztalk.main.domain.entity.status.FriendStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String nickname;

    private String profileImageUrl;


    @Enumerated(EnumType.STRING)
    private FriendStatus status; // 친구 status
    
    
    
    //회원탈퇴시 ->작성한 게시물, 댓글 모두 삭제
    @Builder.Default
    @OneToMany(mappedBy="nickname", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"friend"})
    private List<Board> boardList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "replyNickname", cascade = ALL, orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();


    //연관관계 메소드
    public void addBoard(Board board){
        boardList.add(board);
    }

    public void addReply(Reply reply){
        replyList.add(reply);
    }



}
