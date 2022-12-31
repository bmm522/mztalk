package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplicationReqDto {

    private Long userId;
    private String name;
    private String phone;
    private String email;
    private String job;
    private String bank;
    private String birthday;
    private String account;
    private AuthStatus authStatus;
    private Status status;

    public Application toEntity(){
        Application application = Application.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .job(job)
                .bank(bank)
                .account(account)
                .birthday(birthday)
                .authStatus(AuthStatus.NO)
                .status(Status.YES)
                .build();
        return application;
    }
}
