package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Mentee;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationDto {

    private Long id;
    private MenteeApplicationDto mentee;
    private MentorDto mentor;
    private String name;
    private String phone;
    private String email;
    private String job;
    private String bank;
    private String birthday;
    private String account;
    private AuthStatus authStatus;
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    public Application toEntity(){
        Application application = Application.builder()
                .id(id)
                .mentee(mentee.toEntity())
                .mentor(mentor.toEntity())
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

    public ApplicationDto(Application application,MenteeApplicationDto mentee){
        this.mentee = mentee;
        this.id = application.getId();
        this.name = application.getName();
        this.phone = application.getPhone();
        this.email = application.getEmail();
        this.job = application.getJob();
        this.bank = application.getBank();
        this.account = application.getAccount();
        this.birthday = application.getBirthday();
        this.authStatus = application.getAuthStatus();
        this.status = application.getStatus();
        this.createdDate = application.getCreatedDate();
        this.lastModifiedDate = application.getLastModifiedDate();
    }

    public ApplicationDto(Application application){
        this.id = application.getId();
        this.name = application.getName();
        this.phone = application.getPhone();
        this.email = application.getEmail();
        this.job = application.getJob();
        this.bank = application.getBank();
        this.account = application.getAccount();
        this.birthday = application.getBirthday();
        this.authStatus = application.getAuthStatus();
        this.status = application.getStatus();
        this.createdDate = application.getCreatedDate();
        this.lastModifiedDate = application.getLastModifiedDate();
    }

    @Builder
    public ApplicationDto(Long id, MenteeApplicationDto mentee, MentorDto mentor, String name, String phone, String email, String job, String bank, String account, String birthday, AuthStatus authStatus, Status status, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.mentee = mentee;
        this.mentor = mentor;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.job = job;
        this.bank = bank;
        this.account = account;
        this.birthday = birthday;
        this.authStatus = authStatus;
        this.status = status;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
