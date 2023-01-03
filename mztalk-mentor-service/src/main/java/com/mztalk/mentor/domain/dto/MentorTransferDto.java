package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Mentor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MentorTransferDto {
    
    private Long userId;

    public MentorTransferDto(Mentor mentor) {
        this.userId = mentor.getUserId();
    }



}
