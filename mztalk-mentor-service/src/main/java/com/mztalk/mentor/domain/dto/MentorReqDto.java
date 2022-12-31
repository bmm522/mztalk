package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Mentor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MentorReqDto {

    private Long userId;
    private Status status;

    public Mentor toEntity(){
        Mentor mentor = Mentor.builder()
                .userId(userId)
                .status(Status.YES)
                .build();
        return mentor;
    }
}
