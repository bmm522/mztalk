package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Mentee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenteeReqDto {

    private Long id;
    private String nickname;

    public Mentee toEntity(){
        Mentee mentee = Mentee.builder()
                .id(id)
                .nickname(nickname)
                .build();
        return mentee;
    }
}
