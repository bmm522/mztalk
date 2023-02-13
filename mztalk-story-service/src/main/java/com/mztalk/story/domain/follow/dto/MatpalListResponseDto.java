package com.mztalk.story.domain.follow.dto;

import com.mztalk.story.status.FollowStatus;
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

//    private String fromPostImageUrl;



    public MatpalListResponseDto(Long fromUserId, Long toUserId, String postImageUrl, FollowStatus followStatus, String matpal){
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.postImageUrl = postImageUrl;
        this.followStatus = followStatus;
        this.matpal = matpal;
//        this.fromPostImageUrl = fromPostImageUrl;
    }

}
