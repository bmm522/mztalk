package com.mztalk.main.domain.follow.dto;

import com.mztalk.main.domain.follow.entity.Follow;
import com.mztalk.main.status.FollowStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class MatpalListResponseDto {


    private Long fromUserId;

    private Long toUserId;

    private String postImageUrl;

    private FollowStatus followStatus;

    private String matpal;


    public MatpalListResponseDto(Long fromUserId, Long toUserId, String postImageUrl, FollowStatus followStatus, String matpal){
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.postImageUrl = postImageUrl;
        this.followStatus = followStatus;
        this.matpal = matpal;




    }


}
