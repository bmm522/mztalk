package com.mztalk.loginservice.user.application.register.dto.reqeust;

import com.mztalk.loginservice.user.repository.entity.util.Provider;
import com.mztalk.loginservice.user.repository.entity.util.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ServiceRegisterReqeustDto {

    private String username;
    private String password;
    private String nickname;
    private String email;
    private Role role;

    private Provider provider;
    private String providerId;

    private String status;

    private Long reportCount;

    public void setPassword(String password){
        this.password = password;
    }

    @Builder
    public ServiceRegisterReqeustDto(String username, String password, String nickname, String email, Role role,Provider provider,String providerId, String status, Long reportCount) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.status = status;
        this.reportCount = reportCount;
    }
}
