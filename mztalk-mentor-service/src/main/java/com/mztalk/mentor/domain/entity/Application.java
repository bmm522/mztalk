package com.mztalk.mentor.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ApplicationDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="APPLICATION")
@EntityListeners(AuditingEntityListener.class)
public class Application extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name ="application_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="mentee_id")
    @JsonIgnore
    private Mentee mentee;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "application")
    @JsonIgnore
    private Mentor mentor;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "application")
    private List<File> files;

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

    @Enumerated(EnumType.STRING)
    private AuthStatus authStatus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Application(Long id, Mentee mentee, Mentor mentor, List<File> files, String name, String phone,
                       String email, String job, String bank, String account,
                       AuthStatus authStatus, Status status) {
        this.id = id;
        this.mentee = mentee;
        this.mentor = mentor;
        this.files = files;
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
        this.files = applicationDto.getFiles();
        this.name = applicationDto.getName();
        this.phone = applicationDto.getPhone();
        this.email = applicationDto.getEmail();
        this.job = applicationDto.getJob();
        this.bank = applicationDto.getBank();
        this.account = applicationDto.getAccount();
    }

    //== 연관관계 편의 메소드 ==//
    public void addFile(File file){
        this.files.add(file);
        if(file.getApplication() != this){
            file.addApplication(this);
        }
    }

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
