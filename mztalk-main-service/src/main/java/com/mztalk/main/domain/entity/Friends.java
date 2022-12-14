package com.mztalk.main.domain.entity;

import com.mztalk.main.domain.entity.status.FriendStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Friends {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private FriendStatus status; // 친구관계 status



}
