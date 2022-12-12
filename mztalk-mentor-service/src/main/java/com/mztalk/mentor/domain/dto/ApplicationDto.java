package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Image;
import com.mztalk.mentor.domain.entity.Mentor;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationDto {

    private Long id;
    private Mentor mentor;
    private Image image;
    private String name;
    private String phone;
    private String email;
    private String job;
    private String bank;
    private String account;
    private AuthStatus authStatus;
    private Status status;

    public Application toEntity(){
        Application application = Application.builder()
                .id(id)
                .mentor(mentor)
                .image(image)
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
        this.mentor = application.getMentor();
        this.image = application.getImage();
        this.name = application.getName();
        this.phone = application.getPhone();
        this.email = application.getEmail();
        this.job = application.getJob();
        this.bank = application.getBank();
        this.account = application.getAccount();
        this.authStatus = application.getAuthStatus();
        this.status = application.getStatus();
    }

}
