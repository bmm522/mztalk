package com.mztalk.loginservice.user.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mztalk.loginservice.user.application.login.oauth.MztalkCookie;
import com.mztalk.loginservice.user.application.login.dto.response.ServiceUserInfoResponseDto;
import com.mztalk.loginservice.user.application.login.dto.response.JwtResponseDto;
import com.mztalk.loginservice.chat.repository.entity.Chatroom;
import com.mztalk.loginservice.report.repository.entity.Report;
import com.mztalk.loginservice.user.repository.entity.util.Provider;
import com.mztalk.loginservice.user.repository.entity.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;
    private String providerId;
    @CreatedDate
    private Timestamp createDate;
    private String status;

    private long reportCount;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Report> reports;

    @OneToMany(mappedBy = "fromUser")
    @JsonIgnore
    private List<Chatroom> fromUsers;

    public void changeNickname(String nickname){
        this.nickname = nickname;
    }

    public ServiceUserInfoResponseDto toUserInfoDto(String imageUrl) {
        return ServiceUserInfoResponseDto.builder()
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

    public ServiceUserInfoResponseDto toUserInfoDto() {
        return ServiceUserInfoResponseDto.builder()
                .userId(String.valueOf(id))
                .username(username)
                .nickname(nickname)
                .email(email)
                .role(role)
                .provider(provider)
                .providerId(providerId)
                .createDate(createDate)
                .status(status)
                .reportCount(String.valueOf(reportCount))
                .build();
    }

    public MztalkCookie getUsernameCookieFromMztalk() throws UnsupportedEncodingException {
        return new MztalkCookie(username);
    }

    public MztalkCookie toMztalkCookieWithExistsUser(JwtResponseDto jwtResponseDto) throws UnsupportedEncodingException {
        return new MztalkCookie(jwtResponseDto, id, nickname, status, role);
    }

    public String getRoleValue(){
        return this.role.getRole();
    }


    public String getProviderValue() {
        return this.provider.getProvider();
    }

    public User updatePassword(String password){
        this.password = password;
        return this;
    }

    public User updateStatus(String status) {
        this.status = status;
        return this;
    }

    public User updateRole(Role role) {
        this.role = role;
        return this;
    }

    public User updateNickname(String nickname){
        this.nickname = nickname;
        return this;
    }

    public User updateEmail(String email){
        this.email = email;
        return this;
    }


}
