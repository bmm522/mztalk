package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.entity.Mentee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MenteeApplicationDto {
    private Long id;
    private String nickname;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public MenteeApplicationDto(Mentee mentee){
        this.id = mentee.getId();
        this.nickname = mentee.getNickname();
        this.createdDate = mentee.getCreatedDate();
        this.lastModifiedDate = mentee.getLastModifiedDate();
    }

    public Mentee toEntity(){
        return Mentee.builder()
                .id(id)
                .nickname(nickname)
                .build();
    }
}
