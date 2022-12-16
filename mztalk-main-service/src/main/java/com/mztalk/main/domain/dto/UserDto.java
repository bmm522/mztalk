package com.mztalk.main.domain.dto;

import com.mztalk.main.domain.entity.User;
import lombok.Getter;

@Getter
public class UserDto {

    private Long id;
    private String nickname;


    // User를 반환하는 메소드
    public User toEntity(){
        return User.builder()
                .id(this.getId())
                .nickname(this.getNickname())
                .build();
    }

    //GetMapping
    // (UserDto userDto){ repository.save(userDto.toEntity();}
}
