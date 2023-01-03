package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Participant;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantResDto {

    private Long id;
    private String name;
    private String phone;
    private String message;
    private String email;
    private Status status;
    private MenteeTransferDto mentee;
    private BoardTransferDto board;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ParticipantResDto(Participant participant){
        this.id = participant.getId();
        this.name = participant.getName();
        this.phone = participant.getPhone();
        this.message = participant.getMessage();
        this.email = participant.getEmail();
        this.status = participant.getStatus();
        this.createdDate = participant.getCreatedDate();
        this.lastModifiedDate = participant.getLastModifiedDate();
    }

    public ParticipantResDto(Participant participant, MenteeTransferDto mentee, BoardTransferDto board){
        this.mentee = mentee;
        this.board = board;
        this.id = participant.getId();
        this.name = participant.getName();
        this.phone = participant.getPhone();
        this.message = participant.getMessage();
        this.email = participant.getEmail();
        this.status = participant.getStatus();
        this.createdDate = participant.getCreatedDate();
        this.lastModifiedDate = participant.getLastModifiedDate();
    }

    public ParticipantResDto(Participant participant, MenteeTransferDto mentee){
        this.mentee = mentee;
        this.id = participant.getId();
        this.name = participant.getName();
        this.phone = participant.getPhone();
        this.message = participant.getMessage();
        this.email = participant.getEmail();
        this.status = participant.getStatus();
        this.createdDate = participant.getCreatedDate();
        this.lastModifiedDate = participant.getLastModifiedDate();
    }

}
