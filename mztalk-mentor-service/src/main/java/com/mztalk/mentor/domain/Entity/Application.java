package com.mztalk.mentor.domain.Entity;

import com.mztalk.mentor.domain.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Application extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name ="application_id")
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String job; //현재 소속: 강사, 대학생, 대학원생

    private String bank;

    private String account;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<File> files = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private AuthStatus authStatus;

}
