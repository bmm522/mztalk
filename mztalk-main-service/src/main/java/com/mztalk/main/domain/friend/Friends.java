package com.mztalk.main.domain.friend;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickname;

    @Builder
    public Friends(Long id, String nickname){
        this.id = id;
        this.nickname = nickname;
    }

}
