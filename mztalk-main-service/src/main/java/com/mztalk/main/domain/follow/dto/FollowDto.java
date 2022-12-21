package com.mztalk.main.domain.follow.dto;


import com.mztalk.main.domain.follow.entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {

    private Long userId;
    private String username;
    private BigInteger followState;   //상태여부 boolean
    private BigInteger  equalUserState; //동일인여부
    private String profileImageUrl;
    private String ImageName;



}
