package com.mztalk.login.domain.entity;

import com.mztalk.login.domain.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String role;
    private String provider;
    private String providerId;
    @CreationTimestamp
    private Timestamp createDate;
    private String mentorStatus;
    private String status;
    private String nicknameCheck;

    public void changeNickname(String nickname){
        this.nickname = nickname;
    }

    public UserInfoDto toUserInfoDto() {
        return UserInfoDto.builder()
                .username(username)
                .nickname(nickname)
                .email(email)
                .role(role)
                .provider(provider)
                .providerId(providerId)
                .createDate(createDate)
                .mentorStatus(mentorStatus)
                .status(status)
                .nicknameCheck(nicknameCheck)
                .build();
    }
}
