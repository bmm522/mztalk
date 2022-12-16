package com.mztalk.main.domain.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
            name="Follow_uk",
            columnNames = {"fromUserId","toUserId"}
        )
    }
)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // pk

    @JoinColumn(name="fromUserId")
    private String fromUser;  //내가 친구 추가한 사람
                                //내가 등록한 사람
                                //영어를 못해서...(from)
    @JoinColumn(name="toUserId")
    private String toUser;    //나를 친구로 추가한 사람
                                //나를 등록한 사람
                                //to
}
