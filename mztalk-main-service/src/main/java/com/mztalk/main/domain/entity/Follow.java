package com.mztalk.main.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;  // pk


    private String following;  //내가 친구 추가한 사람
                                //내가 등록한 사람

    private String follower;    //나를 친구로 추가한 사람
                                //나를 등록한 사람
}
