package com.mztalk.main.domain.follow.entity;


import com.mztalk.main.common.BaseTimeEntity;
import com.mztalk.main.status.ProfileImageStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Profile extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   //사진 번호

    private String postImageUrl;  //사진을 전송 받아서 그 사진을 서버에 특정폴더에 저장
                                    //DB에 그 저장된 경로를 insert

    private String nickname;    //유저 이름

    private String profileImageName;

    @Enumerated(EnumType.STRING)
    private ProfileImageStatus profileImageStatus;

    private long own;

}
