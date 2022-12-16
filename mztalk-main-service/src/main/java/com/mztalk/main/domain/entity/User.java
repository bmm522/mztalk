package com.mztalk.main.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@Getter
public class User {

    @Id
    @Column(name = "user_id")
    private Long id; // 지인씨가 스토리지 담아서 줌

    private String nickname; // 지인씨가 스토리지에 담아서 줌

    public User() {
    }

    @Builder
    public User(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }


}

