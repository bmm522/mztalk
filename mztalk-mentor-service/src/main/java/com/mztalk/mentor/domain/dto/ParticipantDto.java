package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Participant;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {

    private Long id;
    private String name;
    private String phone;
    private String message;
    private String email;
    private Status status;
    private MenteeApplicationDto mentee;
    private BoardMenteeDto board;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public Participant toEntity(){
        Participant participant = Participant.builder()
                .id(id)
                .name(name)
                .phone(phone)
                .message(message)
                .email(email)
                .status(Status.YES)
                .build();
        return participant;
    }

    public ParticipantDto(Participant participant){
        this.id = participant.getId();
        this.name = participant.getName();
        this.phone = participant.getPhone();
        this.message = participant.getMessage();
        this.email = participant.getEmail();
        this.status = participant.getStatus();
        this.createdDate = participant.getCreatedDate();
        this.lastModifiedDate = participant.getLastModifiedDate();
    }

    public ParticipantDto(Participant participant,MenteeApplicationDto mentee, BoardMenteeDto board){
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

}
