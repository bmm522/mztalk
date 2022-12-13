package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ApplicationDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="APPLICATION")
@EntityListeners(AuditingEntityListener.class)
public class Application extends com.mztalk.mentor.domain.entity.BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name ="application_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mentee_id")
    private Mentee mentee;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "application")
    private Mentor mentor;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "application")
    private Image image;

    private String name;

    private String phone;

    private String email;

    private String job; //현재 소속: 강사, 대학생, 대학원생

    private String bank;

    private String account;

    @Enumerated(EnumType.STRING)
    private AuthStatus authStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Application(Long id, Mentee mentee, Mentor mentor, Image image, String name, String phone,
                       String email, String job, String bank, String account,
                       AuthStatus authStatus, Status status) {
        this.id = id;
        this.mentee = mentee;
        this.mentor = mentor;
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.job = job;
        this.bank = bank;
        this.account = account;
        this.authStatus = authStatus;
        this.status = status;
    }

    public void updateApplication(ApplicationDto applicationDto){
        this.image = applicationDto.getImage();
        this.name = applicationDto.getName();
        this.phone = applicationDto.getPhone();
        this.email = applicationDto.getEmail();
        this.job = applicationDto.getJob();
        this.bank = applicationDto.getBank();
        this.account = applicationDto.getAccount();
    }

    //== 연관관계 편의 메소드 ==//
    public void addImage(Image image){
        this.image = image;
        image.addApplication(this);
    }

    public void addMentee(Mentee mentee){
        this.mentee = mentee;
        mentee.addApplication(this);
    }

    public void addMentor(Mentor mentor){
        this.mentor = mentor;
        mentor.addApplication(this);
    }

    //== 멘토 지원 신청서 생성 메소드==//
    public static Application createApplication(Mentee mentee){
        Application application = new Application();
        application.addMentee(mentee);
        return application;
    }

}
