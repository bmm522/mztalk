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
public class MentorBoardDto {
    private Long userId;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public MentorBoardDto(Mentor mentor) {
        this.userId = mentor.getUserId();
        this.status = mentor.getStatus();
        this.createdDate = mentor.getCreatedDate();
        this.lastModifiedDate = mentor.getLastModifiedDate();
    }



}
