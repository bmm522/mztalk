package com.mztalk.login.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mztalk.login.domain.cookie.MztalkCookie;
import com.mztalk.login.domain.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;

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
    private String status;

    private long reportCount;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Report> reports;


    public void changeNickname(String nickname){
        this.nickname = nickname;
    }

    public UserInfoDto toUserInfoDto(String imageUrl) {
        return UserInfoDto.builder()
                .userId(String.valueOf(id))
                .username(username)
                .nickname(nickname)
                .email(email)
                .role(role)
                .provider(provider)
                .providerId(providerId)
                .createDate(createDate)
                .status(status)
                .imageUrl(imageUrl)
                .reportCount(String.valueOf(reportCount))
                .build();
    }

    public MztalkCookie getUsernameCookieFromMztalk() throws UnsupportedEncodingException {
        return new MztalkCookie(username);
    }

    public MztalkCookie toMztalkCookieWithExistsUser(ConcurrentHashMap<String, String> jwtMap) throws UnsupportedEncodingException {
        return new MztalkCookie(jwtMap, id, nickname, status);
    }

}
