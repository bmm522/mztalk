package com.mztalk.login.domain.dto;

import com.mztalk.login.domain.entity.User;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private String userId;
    private String password;
    private String nickname;
    private String email;

    public User toUserEntity(BCryptPasswordEncoder bCryptPasswordEncoder){
        return User.builder()
                .username(userId)
                .password(bCryptPasswordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .role("ROLE_USER")
                .provider("LOCAL")
                .providerId("NULL")
                .mentorStatus("N")
                .status("Y")
                .nicknameCheck("Y")
                .build();
    }

}
