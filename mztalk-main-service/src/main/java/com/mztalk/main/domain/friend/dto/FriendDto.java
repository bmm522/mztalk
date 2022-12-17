package com.mztalk.main.domain.friend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendDto {

    Boolean isOneself;
    FriendInfoDto userInfo;



}
