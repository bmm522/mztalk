package com.mztalk.main.domain.follow.dto;

import com.mztalk.main.status.FollowStatus;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowReponseDto {

    private long id;

    private Long fromUserId;

    private Long toUserId;

    private Timestamp createDate;

    private FollowStatus followStatus;

    private String toUserName;




}
