package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Mentee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenteeTransferDto {

    private Long id;
    private String nickname;

    public MenteeTransferDto(Mentee mentee){
        this.id = mentee.getId();
        this.nickname = mentee.getNickname();
    }

}
