package com.mztalk.mentor.domain.entity;

import com.mztalk.mentor.domain.AuthStatus;
import com.mztalk.mentor.domain.Status;
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
    private File file;

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
    public Application(Mentor mentor, File file, String name, String phone,
                       String email, String job, String bank, String account,
                       AuthStatus authStatus, Status status) {
        this.mentor = mentor;
        this.file = file;
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
