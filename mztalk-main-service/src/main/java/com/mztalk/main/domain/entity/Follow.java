package com.mztalk.main.domain.entity;

import lombok.*;
import javax.persistence.*;


//@Table(
//    uniqueConstraints = {
//        @UniqueConstraint(
//            name="Follow_uk",
//            columnNames = {"fromUserId","toUserId"}
//        )
//    }
//)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Follow extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // pk

    //중간 테이블 생성을 위해
    @ManyToOne
    @JoinColumn(name="fromUserNo")
    private Follow fromUser;  //내가 친구 추가한 사람
                                //내가 등록한 사람
                                //영어를 못해서...(from)
    @ManyToOne
    @JoinColumn(name="toUserNo")
    private Follow toUser;    //나를 친구로 추가한 사람
                                //나를 등록한 사람
                                //to

    @Transient
    private boolean matpal;



}
