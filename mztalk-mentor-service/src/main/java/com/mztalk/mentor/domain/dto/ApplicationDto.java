package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.Image;
import com.mztalk.mentor.domain.entity.Mentor;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
