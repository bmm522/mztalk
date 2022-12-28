package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.File;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Mentor;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationDto {

    private Long id;
    private Mentee mentee;
    private Mentor mentor;
    private List<File> files = new ArrayList<>();
    private String name;
    private String phone;
    private String email;
    private String job;
    private String bank;
    private String account;
    private Long userId; // 홈페이지 내 유저 고유 정보
    private AuthStatus authStatus;
    private Status status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    public Application toEntity(){
        Application application = Application.builder()
                .id(id)
                .mentee(mentee)
                .mentor(mentor)
                .files(files)
                .name(name)
                .phone(phone)
                .email(email)
                .job(job)
                .bank(bank)
                .account(account)
                .authStatus(AuthStatus.NO)
                .status(Status.YES)
                .build();
        return application;
    }

    public ApplicationDto(Application application){
        this.id = application.getId();
        this.mentee = application.getMentee();
        this.mentor = application.getMentor();
        this.files = application.getFiles();
        this.name = application.getName();
        this.phone = application.getPhone();
        this.email = application.getEmail();
        this.job = application.getJob();
        this.bank = application.getBank();
        this.account = application.getAccount();
        this.authStatus = application.getAuthStatus();
        this.status = application.getStatus();
        this.createdDate = application.getCreatedDate();
        this.lastModifiedDate = application.getLastModifiedDate();
    }

}
