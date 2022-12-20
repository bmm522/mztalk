package com.mztalk.main.domain.follow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long followId;

    private long fromUserId;

    private long toUserId;
}
