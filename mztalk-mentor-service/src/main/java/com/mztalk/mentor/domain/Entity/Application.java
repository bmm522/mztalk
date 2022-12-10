package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.dto.ApplicationDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="APPLICATION")
public class Application extends com.mztalk.mentor.domain.entity.BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name ="application_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Mentor mentor;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="file_id")
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

    public void addFile(Image image){
        this.image = image;
        image.addApplication(this);
    }

    @Builder
    public Application(Mentor mentor, Image image, String name, String phone,
                       String email, String job, String bank, String account,
                       AuthStatus authStatus, Status status) {
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

    public void change(ApplicationDto applicationDto){
        this.image = applicationDto.getImage();
        this.name = applicationDto.getName();
        this.phone = applicationDto.getPhone();
        this.email = applicationDto.getEmail();
        this.job = applicationDto.getJob();
        this.bank = applicationDto.getBank();
        this.account = applicationDto.getAccount();
    }

}
