package com.mztalk.story.domain.follow.dto;


import com.mztalk.story.domain.follow.Follow;
import com.mztalk.story.status.FollowStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {
    @ApiModelProperty(value="팔로우 식별자", example = "1", required = true)
    private long id;
    @ApiModelProperty(value="팔로우 하는 사람", example = "1", required = true)
    private Long fromUserId;
    @ApiModelProperty(value="팔로우 받는 사람", example = "2", required = true)
    private Long toUserId;
    @ApiModelProperty(value="팔로우 날짜", example = "2023-01-01'T'12:00:00", required = true)
    private Timestamp createDate;
    @ApiModelProperty(value="팔로우 상태 확인", example = "FOLLOWING", required = true)
    private FollowStatus followStatus;
    @ApiModelProperty(value="팔로우 받는 사람 이름", example = "강건강", required = true)
    private String toUserName;
    @ApiModelProperty(value="팔로우 받는 사람 이미지 주소", example = "https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/7276284f-daed-4b0d-9ca3-7a7bb1930138-profile.png", required = true)
    private String toUserUrl;

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

