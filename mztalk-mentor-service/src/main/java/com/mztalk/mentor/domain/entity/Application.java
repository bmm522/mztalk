package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ApplicationResDto;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="APPLICATION")
@EntityListeners(AuditingEntityListener.class)
public class Application extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name ="application_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mentee_id")
    private Mentee mentee;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "application")
    private Mentor mentor;

    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private String job; //현재 소속: 강사, 대학생, 대학원생
    @NotNull
    private String bank;
    @NotNull
    private String account;
    @NotNull
    private String birthday;

    @Enumerated(EnumType.STRING)
    private AuthStatus authStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Application(Long id, Mentee mentee, Mentor mentor, String name, String phone,
                       String email, String job, String bank, String account, String birthday,
                       AuthStatus authStatus, Status status) {
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
    }

    public void updateApplication(ApplicationResDto applicationResDto){
        this.name = applicationResDto.getName();
        this.phone = applicationResDto.getPhone();
        this.email = applicationResDto.getEmail();
        this.job = applicationResDto.getJob();
        this.bank = applicationResDto.getBank();
        this.account = applicationResDto.getAccount();
    }

    //== 연관관계 편의 메소드 ==//
    public void addMentee(Mentee mentee){
        this.mentee = mentee;
        mentee.addApplication(this);
    }

    public void addMentor(Mentor mentor){
        this.mentor = mentor;
    }

    //== 멘토 지원 신청서 승인 메소드==//
    public void changeAuthStatus(){
        this.authStatus = AuthStatus.YES;
    }

}
