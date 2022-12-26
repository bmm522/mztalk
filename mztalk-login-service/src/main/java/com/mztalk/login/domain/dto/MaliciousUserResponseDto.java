package com.mztalk.login.domain.dto;

import com.mztalk.login.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaliciousUserResponseDto {

    private String userNo;
    private String username;
    private String nickname;
    private String createDate;
    private String reportCount;

    public MaliciousUserResponseDto(User user){
        this.userNo = String.valueOf(user.getId());
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.createDate = String.valueOf(user.getCreateDate());
        this.reportCount = String.valueOf(user.getReportCount());
    }

}
