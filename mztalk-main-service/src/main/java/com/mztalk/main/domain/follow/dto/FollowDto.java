package com.mztalk.main.domain.follow.dto;


import com.mztalk.main.domain.follow.entity.Follow;
import com.mztalk.main.status.FollowStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {

    private long id;

    private Long fromUserId;

    private Long toUserId;

    private Timestamp createDate;

    private FollowStatus followStatus;


    public Follow toEntity() {
        Follow follow = Follow.builder()
                .id(id)
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .followStatus(FollowStatus.FOLLOWING)
                .build();

        return follow;
    }

    public FollowDto(Follow follow){
        this.id = follow.getId();
        this.fromUserId = follow.getFromUserId();
        this.toUserId = follow.getToUserId();
        this.followStatus = follow.getFollowStatus();

    }



}

