package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantTransferDto {

    private Long id;
    private String name;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ParticipantTransferDto(Participant participant){
        this.id = participant.getId();
        this.name = participant.getName();
        this.status = participant.getStatus();
        this.createdDate = participant.getCreatedDate();
        this.lastModifiedDate = participant.getLastModifiedDate();
    }
}
