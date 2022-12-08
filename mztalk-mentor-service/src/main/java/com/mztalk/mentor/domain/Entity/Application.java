package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="APPLICATION")
public class Application extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name ="application_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Mentor mentor;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<File> files = new ArrayList<>();

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
    public Application(Mentor mentor, List<File> files, String name, String phone,
                       String email, String job, String bank, String account,
                       AuthStatus authStatus, Status status) {
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
}
